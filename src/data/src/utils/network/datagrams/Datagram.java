package utils.network.datagrams;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import utils.SerializationException;
import utils.Serializer;
import utils.SerializerFactory;
import utils.network.id.IDGenerator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public abstract class Datagram implements Serializable {

    @XStreamOmitField
    protected transient Serializer xmlSerializer = SerializerFactory.getXMLInstance();

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
        this.payload = xmlSerializer.serialize((Serializable) payload);
    }

    public String getId() {
        return id;
    }

    public DatagramType getType() {
        return type;
    }

    private void readObject(ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        xmlSerializer = SerializerFactory.getXMLInstance();
    }
}
