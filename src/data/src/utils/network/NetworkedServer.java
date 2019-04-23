package utils.network;

import utils.ConnectableServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;

/**
 * Server class for hosting a client connection and running the network interface.
 * @author Jake Mullett
 */
public class NetworkedServer extends NetworkedBase implements ConnectableServer {

    private static final String ACCEPTOR_THREAD_FOR_SERVER_FAILED = "Acceptor thread for server failed! ";

    public NetworkedServer(Object parent) {
        super(parent);
    }

    public NetworkedServer(Object parent, int port) {
        super(parent);
        accept(port);
    }

    @Override
    public void accept(int port) {
        if (socket == null) {
            Thread acceptThread = new Thread(() -> {
                try {
                    socket = new ServerSocket(port).accept();
                    createStreams();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, ACCEPTOR_THREAD_FOR_SERVER_FAILED + ex.getMessage());
                }
            });
            acceptThread.start();
        }
    }
}
