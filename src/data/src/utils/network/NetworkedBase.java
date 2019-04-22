package utils.network;

import utils.Connectable;
import utils.NetworkException;
import utils.SerializationException;
import utils.network.datagrams.Datagram;
import utils.network.datagrams.Request;
import utils.network.datagrams.Response;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class which implements shared utilites between the client and the server.
 * Its primary function is to read/write requests, and reply with Responses containing the result of the method
 * calls if required.
 * @author Jake Mullett
 */
public abstract class NetworkedBase implements Connectable {

    private static final String ERROR_IN_CLOSING_CONNECTION = "Error in closing connection! ";
    private static final String DATAGRAM_FROM_NETWORK_ERROR = "Error in trying to process datagram from network. Error: \n";
    private static final String METHOD_CALL_HAS_TIMED_OUT = "Method call has timed out.";
    private static final String BLOCKING_REQUEST_ERR_MSG = "Error in sending blocking request for method ";
    private static final String NON_BLOCKING_ERR_MSG = "Error in sending non-blocking request for method ";
    private static final String EOF_EXCEPTION = "Socket/EOF exception, likely due to closed port on other side. Closing reading thread. ";
    private static final int TIMEOUT_REQUEST_MS = 500;
    private static final int WAIT_PER_CHECK_MS = 1;
    private static final int READ_INTERVAL_MS = 50;

    Logger LOGGER = Logger.getGlobal();
    private ConcurrentMap<String, Response> requestPool;

    protected Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Thread readerThread;
    private Object parent;

    NetworkedBase(Object parentObject) {
        parent = parentObject;
        requestPool = new ConcurrentHashMap<>();
    }

    /**
     * Send a request which expects a response, and thus has to wait for the response from the network.
     * Package-private.
     * @param request request to be made.
     * @throws NetworkException Exception in trying to transmit request, contains information on origination.
     */
    Object sendRequest(Request request) throws NetworkException {
        try {
            sendDatagram(request);
            return request.requiresResponse() ? getResponse(request.getId()) : null;
        } catch (Exception ex) {
            // Wrap exception so that anyone calling this only has one exception type to catch.
            // TODO: make logging better
            LOGGER.log(Level.SEVERE, ex.getMessage());
            throw new NetworkException(ex.getMessage(), ex);
        }
    }

    // Throws Exception as our response could be any kind of exception.
    private Object getResponse(String id) throws Exception {
        int count = 0;
        // TODO: make this async/not use sleep.
        while(!requestPool.containsKey(id)) {
            if (count >= TIMEOUT_REQUEST_MS/WAIT_PER_CHECK_MS) {
                throw new TimeoutException(METHOD_CALL_HAS_TIMED_OUT);
            }
            Thread.sleep(WAIT_PER_CHECK_MS);
            count++;
        }
        Object result = requestPool.remove(id).getResult();
        // If we got an error as a result, throw it instead of returning it.
        if (result instanceof Exception) {
            throw ((Exception) result);
        }
        return result;
    }

    private void sendDatagram(Datagram datagram) throws IOException {
        objectOutputStream.writeObject(datagram);
    }

    public void disconnect() {
        try {
            if (socket != null) {
                objectInputStream.close();
                objectOutputStream.close();
                socket.close();
                readerThread.interrupt();
            }
        } catch (IOException ex) {
            // Because this is running in a separate thread, we cannot bubble exceptions and have
            // the user see them.
            LOGGER.log(Level.WARNING, ERROR_IN_CLOSING_CONNECTION + ex.getMessage());
        } finally {
            readerThread.interrupt();
        }
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    public Class getServiceInterface() {
        return parent.getClass();
    }

    /**
     * This method should be called by the child when the socket is instantiated upon connection.
     * Reads requests from the input stream and passes them to the callParent method for deserialization.
     */
    private void createInputReaderThread() {
        readerThread = new Thread(() -> {
            boolean socketAlive = true;
            while (socketAlive) {
                try {
                    readDatagrams();
                    Thread.sleep(READ_INTERVAL_MS);
                } catch (SocketException | EOFException sockEx) {
                    LOGGER.log(Level.INFO, EOF_EXCEPTION);
                    socketAlive = false;
                } catch (Exception ex) {
                    // Again, this is a separate thread so we
                    // cannot pass this along to the user/GUI.
                    // Log the exception and try to continue.
                    LOGGER.log(Level.SEVERE, DATAGRAM_FROM_NETWORK_ERROR + ex.getMessage());
                }
            }
        });
        readerThread.start();
    }

    private void readDatagrams() throws IOException, ClassNotFoundException, SerializationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Datagram datagram = readDatagram();
        while (datagram != null) {
            switch (datagram.getType()) {
                case REQUEST:
                    sendDatagram(((Request)datagram).applyRequest(parent));
                    break;
                case RESPONSE:
                    handleResponse((Response) datagram);
                    break;
            }
            datagram = readDatagram();
        }
    }

    private Datagram readDatagram() throws IOException, ClassNotFoundException {
        return (Datagram) objectInputStream.readObject();
    }

    private void handleResponse(Response response) {
        requestPool.put(response.getId(), response);
    }


    protected void createStreams() throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush(); // flush to send header for initialiation of input stream
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        createInputReaderThread();
    }

}
