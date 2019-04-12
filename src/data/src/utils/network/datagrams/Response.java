package utils.network.datagrams;

import utils.SerializationException;

/**
 * Class for returning objects from method calls that were called from the other side of the wire.
 * @author Jake Mullett
 */
public class Response extends Datagram {

    public Response(String id, Object result) throws SerializationException {
        super(DatagramType.RESPONSE, id, result);
    }

    public Object getResult() throws SerializationException {
        return xmlSerializer.deserialize(payload);
    }
}
