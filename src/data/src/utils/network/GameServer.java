package utils.network;

import utils.NetworkedServerInterface;

import java.io.IOException;
import java.net.ServerSocket;

public class GameServer extends NetworkInterfacer implements NetworkedServerInterface {

    public GameServer(Object parent) {
        super(parent);
    }

    public GameServer(Object parent, int port) throws IOException {
        super(parent);
        accept(port);
    }

    @Override
    public void accept(int port) throws IOException {
        socket = new ServerSocket(port).accept();
        createStreams();
    }

}
