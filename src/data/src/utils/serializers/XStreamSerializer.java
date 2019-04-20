package utils.serializers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;
import utils.SerializationException;

import java.io.Serializable;

/**
 * Serializer implementation using XStream.
 * @author Jake Mullett
 */
public class XStreamSerializer extends SerializerBase {

    private XStream xStream;

    /**
     * Serializer using the PureJavaReflectionProvider instead of the DomDriver
     * so that default transient fields are instantiated.
     */
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
    public Object deserialize(String object) throws SerializationException {
        try {
            return xStream.fromXML(object);
        } catch (Exception exception) {
            throw new SerializationException(DESERIALIZATION_ERR + exception.getMessage());
        }
    }
}
