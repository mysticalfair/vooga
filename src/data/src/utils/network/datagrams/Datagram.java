package utils.network.datagrams;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import utils.SerializationException;
import utils.Serializer;
import utils.SerializerSingleton;
import utils.network.id.IDGenerator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Base class for instantiating requests or responses on the network.
 * @author Jake Mullett
 */
public abstract class Datagram implements Serializable {

    @XStreamOmitField
    protected transient Serializer serializer = SerializerSingleton.getInstance();

    protected String payload;
    protected String id;

    private DatagramType type;

    Datagram(DatagramType datagramType, String id, Object payload) throws SerializationException {
        this.id = id;
        init(datagramType, payload);
    }

    Datagram(DatagramType datagramType, Object payload) throws SerializationException {
        this.id = IDGenerator.createID();
        init(datagramType, payload);
    }

    private void init(DatagramType datagramType, Object payload) throws SerializationException {
        type = datagramType;
        this.payload = serializer.serialize((Serializable) payload);

    }

    public String getId() {
        return id;
    }

    public DatagramType getType() {
        return type;
    }

    /**
     * When reading the class off the network, also attach the Serializer instance.
     */
    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        serializer = SerializerSingleton.getInstance();
    }
}
