package utils;

import utils.network.GameBase;
import utils.network.GameClient;
import utils.network.GameServer;
import utils.network.NetworkedInterfaceWrapper;

import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * Factory for creating new networked interfaces.
 *
 * The idea of these classes is to abstract the networking such that you can connect to any arbitrary Java class
 * using these servers/clients. Calling methods on the client/server wrapper will instruct this object to
 * pass them along to the actual instance of that interface that you are connected to.
 *
 * Once you have built either a client or a server, you should cast it to the desired interfaces that
 * were passed into the method. e.g.:
 * var player = (NetworkedServerInterface & Player) NetworkFactory.buildServer(Player.class, this, 1234);
 *
 * Then, once you have connected to the corresponding interface (in this example you are the server and they
 * connect to you), you can call any method that the provided interface provides:
 * player.addTower(...);
 */
public class NetworkFactory {

    /**
     * Constructs a client for connecting to a server and acting like the desired interface.
     * @param interfaceToImplement Interface that the server we are connecting to is implementing.
     * @param parentInstance Class that calls from the server should be forwarded to. Usually just pass back 'this'.
     * @return NetworkedClientInterface interfacer
     * @throws IOException Exceptions on trying to connect/construct sockets.
     */
    public static NetworkedClientInterface buildClient(Class<?> interfaceToImplement, Object parentInstance) throws IOException{
        return buildClient(interfaceToImplement, parentInstance, null, 0);
    }

    /**
     * Constructs a client for connecting to a server and acting like the desired interface.
     * Also connects to the server provided.
     * @param interfaceToImplement Interface that the server we are connecting to is implementing.
     * @param parentInstance Class that calls from the server should be forwarded to. Usually just pass back 'this'.
     * @param ip String of ip to connect to of the server
     * @param port port of the server for this client
     * @return NetworkedClientInterface interfacer
     * @throws IOException Exceptions on trying to connect/construct sockets.
     */
    public static NetworkedClientInterface buildClient(Class<?> interfaceToImplement, Object parentInstance, String ip, int port) throws IOException {
        Class[] interfaces = new Class[] {NetworkedClientInterface.class, interfaceToImplement};
        GameBase gameBase = (ip == null || port == 0) ? new GameClient(parentInstance) : new GameClient(parentInstance, ip, port);
        return (NetworkedClientInterface) Proxy.newProxyInstance(
                NetworkedInterfaceWrapper.class.getClassLoader(),
                interfaces,
                new NetworkedInterfaceWrapper(gameBase));
    }

    /**
     * Constructs a server for listening on a desired port for connections from the expected interface provided.
     * Only one port per server instance, so for mulitple players you should have an instance per player.
     * @param interfaceToImplement Interface of the client that will connect
     * @param parentInstance Parent that method calls from the client should be passed to
     * @param port Port to listen on
     * @return NetworkedServerInterface interfacer
     * @throws IOException Exceptions on setting up listening/sockets.
     */
    public static NetworkedServerInterface buildServer(Class<?> interfaceToImplement, Object parentInstance, int port) throws IOException {
        Class[] interfaces = new Class[] {NetworkedServerInterface.class, interfaceToImplement};
        GameBase gameBase = new GameServer(parentInstance, port);
        return (NetworkedServerInterface) Proxy.newProxyInstance(
                NetworkedInterfaceWrapper.class.getClassLoader(),
                interfaces,
                new NetworkedInterfaceWrapper(gameBase));
    }
}
