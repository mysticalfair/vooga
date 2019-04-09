package utils.network.datagrams;

import utils.SerializationException;

public class Response extends Datagram {

    public Response(String id, Object result) throws SerializationException {
        super(DatagramType.RESPONSE, id, result);
    }

    public Object getResult() throws SerializationException {
        return xmlSerializer.deserialize(payload);
    }
}
