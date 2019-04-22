package utils;

public interface Connectable {

    /**
     * Disconnects the client from the server it is currently connected to.
     */
    public void disconnect();


    /**
     * @return Returns if this client is currently connected to a server or not.
     */
    public boolean isConnected();


    /**
     * @return Class type that this Connectable object is representing and passing method calls to.
     */
    public Class getServiceInterface();
}