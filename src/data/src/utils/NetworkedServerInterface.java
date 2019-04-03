package utils;

import java.io.IOException;

/**
 * Interface for operating a game server,
 */
public interface NetworkedServerInterface {

    public void accept(int port) throws IOException;

    public void disconnect();

}
