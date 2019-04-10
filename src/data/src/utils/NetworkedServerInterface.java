package utils;

/**
 * Interface for operating a game server.
 */
public interface NetworkedServerInterface {

    /**
     * Non-blocking call to open a socket at the provided port.
     * Use isConnected to see if the client has connected yet.
     * @param port
     */
    public void accept(int port);

    public boolean isConnected();

    public void disconnect();

}
