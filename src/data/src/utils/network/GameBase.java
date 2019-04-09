package utils.network;

import utils.SerializationException;
import utils.network.datagrams.Datagram;
import utils.network.datagrams.Request;
import utils.network.datagrams.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeoutException;

/**
 * Class which implements shared utilites between the client and the server.
 * Its primary function is to read and write requests
 */
public abstract class GameBase {

    private static final String ERROR_IN_CLOSING_CONNECTION = "Error in closing connection! ";
    private static final String DATAGRAM_FROM_NETWORK_ERROR = "Error in trying to process datagram from network. Error: \n";

    private ConcurrentMap<String, Response> requestPool;

    protected Socket socket;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    private Thread readerThread;
    private Object parent;

    GameBase(Object parentObject) {
        parent = parentObject;
        requestPool = new ConcurrentHashMap<>();
    }

    /**
     * Package-private.
     * @param method
     * @param args
     * @throws IOException
     */
    void sendNonBlockingRequest(Method method, Object[] args) throws IOException, SerializationException {
        sendRequest(method, args);
    }

    Object sendBlockingRequest(Method method, Object[] args) throws IOException, InterruptedException, TimeoutException, SerializationException {
        String sentRequestID = sendRequest(method, args).getId();
        int count = 0;
        while(!requestPool.containsKey(sentRequestID)) {
            if (count >= 500)
                throw new TimeoutException("Method call has timed out.");
            Thread.sleep(1);
            count++;
        }
        return requestPool.remove(sentRequestID).getResult();
    }

    private Request sendRequest(Method method, Object[] args) throws IOException, SerializationException {
        Request request = new Request(method, args);
        sendDatagram(request);
        return request;
    }

    private void sendDatagram(Datagram datagram) throws IOException {
        objectOutputStream.writeObject(datagram);
    }

    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
                objectInputStream.close();
                objectOutputStream.close();
                readerThread.interrupt();
            }
        } catch (IOException ex) {
            // Because this is running in a separate thread, we cannot bubble exceptions and have
            // the user see them.
            System.out.println(ERROR_IN_CLOSING_CONNECTION + ex.getMessage());
        } finally {
            readerThread.interrupt();
        }
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }

    /**
     * This method should be called by the child when the socket is instantiated upon connection.
     * Reads requests from the input stream and passes them to the callParent method for deserialization.
     */
    private void createInputReaderThread() {
        Runnable runnable = () -> {
            while (true) {
                try {
                    readDatagrams();
                    Thread.sleep(50);
                } catch (Exception ex) {
                    // Again, this is a separate thread so we cannot pass this along to the user/GUI.
                    // Log the exception and try to continue.
                    System.out.println(DATAGRAM_FROM_NETWORK_ERROR + ex.getMessage());
                }
            }
        };
        readerThread = new Thread(runnable);
        readerThread.start();
    }

    private void readDatagrams() throws IOException, ClassNotFoundException, SerializationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Datagram datagram = readDatagram();
        while (datagram != null) {
            switch (datagram.getType()) {
                case REQUEST:
                    handleRequest((Request) datagram);
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

    private void handleRequest(Request request) throws SerializationException, IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object returnObject = callParent(request);
        if (request.requiresResponse()) {
            Response response = new Response(request.getId(), returnObject);
            sendDatagram(response);
        }
    }

    private void handleResponse(Response response) {
        requestPool.put(response.getId(), response);
    }

    private Object callParent(Request request) throws SerializationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Method methodToCall = parent.getClass().getMethod(request.getMethod(), request.getArgClassTypes());
        return methodToCall.invoke(parent, request.getArgs());
    }

    protected void createStreams() throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush(); // flush to send header for initialiation of input stream
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        createInputReaderThread();
    }
}
