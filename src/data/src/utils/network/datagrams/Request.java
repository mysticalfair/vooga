package utils.network.datagrams;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import utils.SerializationException;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class for sending method calls across the network, these are sent via TCP.
 * @author Jake Mullett
 */
public class Request extends Datagram {

    private boolean requiresResponse;

    @XStreamOmitField
    private transient UnaryOperator<Object> operation;

    public Request(Method method, Object[] args) throws SerializationException {
        super(DatagramType.REQUEST);
        // Methods cannot be serialized directly, so instead we wrap it in a lambda.
        this.payload = serializer.serialize((Serializable & UnaryOperator<Object>) (parent) -> {
            try {
                return method.invoke(parent, args);
            } catch (Exception ex) {
                Logger.getGlobal().log(Level.SEVERE, "Error in invoking method " + method.getName() + ex.getMessage());
                return ex;
            }
        });
        this.requiresResponse = !method.getReturnType().equals(Void.TYPE);
    }

    public Response applyRequest(Object target) throws SerializationException {
        if (operation == null) {
            operation = (UnaryOperator<Object>)serializer.deserialize(payload);
        }
        Object result = operation.apply(target);
        return requiresResponse ? new Response(id, result) : null;
    }

    public boolean requiresResponse() {
        return requiresResponse;
    }
}
