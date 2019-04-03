package utils;

import utils.network.GameClient;
import utils.network.GameServer;
import utils.network.NetworkInterfacer;
import utils.network.NetworkedInterfaceWrapper;

import java.io.IOException;
import java.lang.reflect.Proxy;

public class NetworkFactory {

    public static NetworkedClientInterface buildClient(Class<?> interfaceToImplement, Object parentInstance) throws IOException{
        return buildClient(interfaceToImplement, parentInstance, null, 0);
    }

    public static NetworkedClientInterface buildClient(Class<?> interfaceToImplement, Object parentInstance, String ip, int port) throws IOException {
        Class[] interfaces = new Class[] {NetworkedClientInterface.class, interfaceToImplement};
        NetworkInterfacer networkInterfacer = (ip == null || port == 0) ? new GameClient(parentInstance) : new GameClient(parentInstance, ip, port);
        return (NetworkedClientInterface) Proxy.newProxyInstance(
                NetworkedInterfaceWrapper.class.getClassLoader(),
                interfaces,
                new NetworkedInterfaceWrapper(parentInstance, networkInterfacer));
    }

    public static NetworkedServerInterface buildServer(Class<?> interfaceToImplement, Object parentInstance, int port) throws IOException {
        Class[] interfaces = new Class[] {NetworkedServerInterface.class, interfaceToImplement};
        NetworkInterfacer networkInterfacer = new GameServer(parentInstance, port);
        return (NetworkedServerInterface) Proxy.newProxyInstance(
                NetworkedInterfaceWrapper.class.getClassLoader(),
                interfaces,
                new NetworkedInterfaceWrapper(parentInstance, networkInterfacer));
    }
}
