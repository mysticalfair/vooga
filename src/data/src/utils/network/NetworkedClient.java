package utils.network;

import utils.ConnectableClient;
import utils.NetworkException;
import utils.network.datagrams.Request;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * Client class for connecting to a server and running the network interface.
 * @author Jake Mullett
 */
public class NetworkedClient extends NetworkedBase implements ConnectableClient {

    private static final String INTERFACE_MISMATCH = "Server interface did not match the expected interface %s!";
    private static final String CONNECTION_ERROR = "Error in attempting to connect to server %s on port %d";
    private static final String INTERFACE_RETRIEVAL_ERROR = "Error in getting server interface, error: %s";
    private static final String GET_CLASS = "getClass";

    public NetworkedClient(Object parentObject) {
        super(parentObject);
    }

    public NetworkedClient(Object parentObject, String ip, int port) throws NetworkException {
        super(parentObject);
        connect(ip, port);
    }

    @Override
    public void connect(String ip, int port, Class interfaceKlazz) throws NetworkException {
        connect(ip, port);
        Object serverIface = getServerIface();
        if (!serverIface.equals(interfaceKlazz)) {
            throw new NetworkException(INTERFACE_MISMATCH, interfaceKlazz.toString());
        }
    }

    @Override
    public void connect(String ip, int port) throws NetworkException {
        if (socket == null) {
            try {
                socket = new Socket(ip, port);
                createStreams();
            } catch (IOException ex) {
                throw new NetworkException(CONNECTION_ERROR, ex, ip, port);
            }
        }
    }

    private Object getServerIface() throws NetworkException {
        try {
            Method getIface = Object.class.getMethod(GET_CLASS);
            return sendRequest(new Request(getIface, null));
        } catch (Throwable ex) {
            throw new NetworkException(INTERFACE_RETRIEVAL_ERROR, ex, ex.getMessage());
        }
    }

}
