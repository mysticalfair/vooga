package utils;

import java.io.IOException;

/**
 * Interface which will be implemented as well as the interface for whatever player,
 * gameengine, or other interface this class is representing.
 * On instantiation, the class should be passed the object that it will be calling methods on.
 * @author Jake Mullett
 */
public interface NetworkedClientInterface {

    /**
     * Disconnects the client from the server it is currently connected to.
     */
    public void disconnect();


    /**
     * @return Returns if this client is currently connected to a server or not.
     */
    public boolean isConnected();

    /**
     * Connects this client to a listening client on the specified ip and port. Returns false on unable to connect.
     */
    public void connect(String ip, int port) throws IOException;

}
