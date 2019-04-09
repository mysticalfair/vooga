package utils.network;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * This class is used to wrap
 */
public class NetworkedInterfaceWrapper implements InvocationHandler {

    private GameBase networkInterface;

    public NetworkedInterfaceWrapper(GameBase networkInterface) {
        this.networkInterface = networkInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        Method[] networkMethods = networkInterface.getClass().getDeclaredMethods();
        if (contains(networkMethods, method)) {
            // if the method is a network command like connect, disconnect etc, call it.
            return method.invoke(networkInterface, args);
        } else {
            // else, send the request to the other side.
            if (method.getReturnType().equals(Void.TYPE)) {
                networkInterface.sendNonBlockingRequest(method, args);
                return null;
            } else {
                return networkInterface.sendBlockingRequest(method, args);
            }
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
