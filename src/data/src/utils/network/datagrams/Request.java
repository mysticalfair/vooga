package utils.network.datagrams;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import utils.SerializationException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for sending method calls across the network, these are sent via TCP.
 * @author Jake Mullett
 */
public class Request extends Datagram {

    private String method;
    private boolean requiresResponse;

    @XStreamOmitField
    private transient Object[] deserialzedPayload;

    public Request(Method method, Object[] args) throws SerializationException {
        super(DatagramType.REQUEST, args);
        this.method = method.getName();
        this.requiresResponse = !method.getReturnType().equals(Void.TYPE);
    }

    public String getMethod() {
        return method;
    }

    public Object[] getArgs() throws SerializationException {
        if (payload == null) {
            return null;
        }
        // We want to avoid repeatedly deserializing the same object, so store for runtime boost.
        if (deserialzedPayload == null)
            deserialzedPayload = (Object[]) xmlSerializer.deserialize(payload);
        return deserialzedPayload;
    }

    public Class<?>[] getArgClassTypes() throws SerializationException {
        Object[] params = getArgs();
        if (params == null) {
            return null;
        }
        List<Class<?>> arr = new ArrayList<>();
        Arrays.stream(params).forEach(param -> arr.add(param.getClass()));
        return arr.toArray(Class<?>[]::new);
    }

    public boolean requiresResponse() {
        return requiresResponse;
    }
}
