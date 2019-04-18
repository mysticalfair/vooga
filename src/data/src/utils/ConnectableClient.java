package utils;

/**
 * Interface which will be implemented as well as the interface for whatever player,
 * authoring, or other interface this class is representing.
 * On instantiation, the class should be passed the object that it will be calling methods on.
 * @author Jake Mullett
 */
public interface ConnectableClient extends Connectable {

    /**
     * Connects this client to a listening client on the specified ip and port. Returns false on unable to connect.
     */
    public void connect(String ip, int port) throws NetworkException;
}
