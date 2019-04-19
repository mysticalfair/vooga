package utils.network.datagrams;

import utils.SerializationException;

import java.io.Serializable;

/**
 * Class for returning objects from method calls that were called from the other side of the wire.
 * @author Jake Mullett
 */
public class Response extends Datagram {

    private String payload;

    public Response(String id, Object result) throws SerializationException {
        super(DatagramType.RESPONSE, id);
        payload = serializer.serialize((Serializable)result);
    }

    public Object getResult() throws SerializationException {
        return serializer.deserialize(payload);
    }
}
