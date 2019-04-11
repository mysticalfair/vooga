package utils.network;

import utils.NetworkedClientInterface;

import java.io.IOException;
import java.net.Socket;

/**
 * Client class for connecting to a server and running the network interface.
 * @author Jake Mullett
 */
public class GameClient extends GameBase implements NetworkedClientInterface {

    public GameClient(Object parentObject) {
        super(parentObject);
    }

    public GameClient(Object parentObject, String ip, int port) throws IOException {
        super(parentObject);
        connect(ip, port);
    }

    @Override
    public void connect(String ip, int port) throws IOException {
        if (socket == null) {
            socket = new Socket(ip, port);
            createStreams();
        }
    }
}
