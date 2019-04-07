package utils.network;

import utils.SerializationException;
import utils.Serializer;
import utils.SerializerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class NetworkInterfacer {

    private static final String ERROR_IN_CLOSING_CONNECTION = "Error in closing connection! ";
    private static final String COULD_NOT_BE_SERIALIZED = "could not be serialized!";
    protected Serializer serializer;
    protected Socket socket;
    protected ObjectOutputStream objectOutputStream;
    protected ObjectInputStream objectInputStream;
    private Thread readerThread;
    private Object parent;

    public NetworkInterfacer(Object parentObject) {
        serializer = SerializerFactory.buildXMLInstance();
        parent = parentObject;
    }

    public void sendRequest(Method method, Object[] args) throws IOException {
        List<String> arr = new ArrayList<>();
        Arrays.stream(args).forEach(object -> {
            try {
                arr.add(serializer.serialize((Serializable)object));
            } catch (SerializationException ex) {
                System.out.println(object.toString() + COULD_NOT_BE_SERIALIZED);
            }
        });
        String[] objects = arr.toArray(String[]::new);
        INetRequest request = new INetRequest(method.getName(), objects);
        objectOutputStream.writeObject(request);
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
    protected void createInputReaderThread() {
        Runnable runnable = () -> {
            while (true) {
                try {
                    readRequests();
                    Thread.sleep(50);
                } catch (Exception ex) {
                    // Again, this is a separate thread so we cannot pass this along to the user/GUI.
                    // Log the exception and try to continue.
                    System.out.println("Error in trying to process request from network. Error: \n" + ex.getMessage());
                }
            }
        };
        readerThread = new Thread(runnable);
        readerThread.start();
    }

    private void readRequests() throws IOException, ClassNotFoundException, SerializationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        Object request = objectInputStream.readObject();
        while (request != null) {
            INetRequest requestObject = (INetRequest) request;
            callParent(requestObject);
            request = objectInputStream.readObject();
        }
    }

    protected Object callParent(INetRequest request) throws SerializationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Object[] params = deserializeParams(request.getArgs());
        List<Class<?>> arr = new ArrayList<>();
        Arrays.stream(params).forEach(param -> arr.add(param.getClass()));
        Class<?>[] paramTypes = arr.toArray(Class<?>[]::new);
        Method methodToCall = parent.getClass().getMethod(request.getMethod(), paramTypes);
        return methodToCall.invoke(parent, params);

    }

    protected void createStreams() throws IOException {
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush(); // flush to send header for initialiation of input stream
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        createInputReaderThread();
    }

    private Object[] deserializeParams(String[] params) throws SerializationException {
        Object[] arr = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            arr[i] = serializer.deserialize(params[i], String.class);
        }
        return arr;
    }
}
