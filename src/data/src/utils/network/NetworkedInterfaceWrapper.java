package utils.network;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * This class is used to wrap the game client or server class with logic for determining what kind
 * of request to send, as well as passing normal method calls such as disconnect() to the local instance.
 * @author Jake Mullett
 */
public class NetworkedInterfaceWrapper implements InvocationHandler {

    private GameBase networkInterface;

    public NetworkedInterfaceWrapper(GameBase networkInterface) {
        this.networkInterface = networkInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        Method[] networkMethods = networkInterface.getClass().getDeclaredMethods();
        // if the method is a network command like connect, disconnect etc, call it.
        if (contains(networkMethods, method)) {
            return method.invoke(networkInterface, args);
        }
        // else, send the request to the other side.
        if (method.getReturnType().equals(Void.TYPE)) {
            networkInterface.sendNonBlockingRequest(method, args);
            return null;
        } else {
            return networkInterface.sendBlockingRequest(method, args);
        }
    }

    private boolean contains(Object[] arr, Object obj) {
        for (Object o : arr) {
            if (o.equals(obj)) {
                return true;
            }
        }
        return false;
    }

}
