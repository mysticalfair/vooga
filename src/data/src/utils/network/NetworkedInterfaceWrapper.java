package utils.network;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * This class is used to wrap
 */
public class NetworkedInterfaceWrapper implements InvocationHandler {

    private NetworkInterfacer networkInstance;

    public NetworkedInterfaceWrapper(NetworkInterfacer networkInstance) {
        this.networkInstance = networkInstance;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
        Method[] networkMethods = networkInstance.getClass().getDeclaredMethods();
        if (contains(networkMethods, method)) {
            // if the method is a network command like connect, disconnect etc, call it.
            return method.invoke(networkInstance, args);
        } else {
            // else, send the request to the other side.
            networkInstance.sendRequest(method, args);
            // Currently none of the API have return objects.
            // If they do, we need to figure that out here.
            return null;
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
