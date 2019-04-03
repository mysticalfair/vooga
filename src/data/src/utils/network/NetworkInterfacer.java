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
                System.out.println(object.toString() + "could not be serialized!");
            }
        });
        String[] objects = arr.toArray(String[]::new);
        INetRequest request = new INetRequest(method.getName(), objects);
        objectOutputStream.writeObject(request);
    }

    // TODO change this from printstacktrace
    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
                objectInputStream.close();
                objectOutputStream.close();
                readerThread.interrupt();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method should be called by the child when the socket is instantiated upon connection.
     * Reads requests from the input stream and passes them to the callParent method for deserialization.
     */
    protected void createInputReaderThread() {
        Runnable runnable = () -> {
            while (true) {
                try {
                    while (objectInputStream.available() > 0) {
                        INetRequest request = (INetRequest) objectInputStream.readObject();
                        callParent(request);
                    }
                    Thread.sleep(50);
                } catch (Exception ex) {
                    //TODO: log somewhere
                    ex.printStackTrace();
                }
            }
        };
        readerThread = new Thread(runnable);
        readerThread.start();
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
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        createInputReaderThread();
    }

    private Object[] deserializeParams(String[] params) throws SerializationException {
        Object[] arr = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            arr[i] = serializer.deserialize(params[i], null);
        }
        return arr;
    }
}
