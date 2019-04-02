package utils.gson;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import utils.SerializationException;
import utils.Serializer;

import java.io.*;

public class XStreamSerializer extends SerializerBase implements Serializer {

    private XStream xStream;

    public XStreamSerializer() {
        xStream = new XStream(new DomDriver());
    }

    @Override
    public String serialize(Serializable object) throws SerializationException {
        try {
            return xStream.toXML(object);
        } catch (Exception exception) {
            throw new SerializationException(SERIALIZATION_ERR + exception.getMessage());
        }
    }

    @Override
    public Serializable deserialize(String object, Class<? extends Serializable> objectType) throws SerializationException {
        try {
            return (Serializable) xStream.fromXML(object);
        } catch (Exception exception) {
            throw new SerializationException(DESERIALIZATION_ERR + exception.getMessage());
        }
    }
}
