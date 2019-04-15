package utils.network;

import utils.Connectable;
import utils.ConnectableClient;
import utils.NetworkException;

import java.io.IOException;
import java.net.Socket;

/**
 * Client class for connecting to a server and running the network interface.
 * @author Jake Mullett
 */
public class NetworkedClient extends NetworkedBase implements ConnectableClient {

    public NetworkedClient(Object parentObject) {
        super(parentObject);
    }

    public NetworkedClient(Object parentObject, String ip, int port) throws NetworkException {
        super(parentObject);
        connect(ip, port);
    }

    @Override
    public void connect(String ip, int port) throws NetworkException {
        if (socket == null) {
            try {
                socket = new Socket(ip, port);
                createStreams();
            } catch (IOException ex) {
                throw new NetworkException("Error in attempting to connect to server " + ip + " on port " + port, ex);
            }
        }
    }
}
