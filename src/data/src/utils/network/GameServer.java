package utils.network;

import utils.NetworkedServerInterface;

import java.io.IOException;
import java.net.ServerSocket;

public class GameServer extends GameBase implements NetworkedServerInterface {

    private static final String ACCEPTOR_THREAD_FOR_SERVER_FAILED = "Acceptor thread for server failed! ";

    public GameServer(Object parent) {
        super(parent);
    }

    public GameServer(Object parent, int port) {
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
                    System.out.println(ACCEPTOR_THREAD_FOR_SERVER_FAILED + ex.getMessage());
                }
            });
            acceptThread.start();
        }
    }
}
