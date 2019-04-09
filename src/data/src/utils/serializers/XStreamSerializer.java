package utils.serializers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.io.xml.DomDriver;
import utils.SerializationException;
import utils.Serializer;

import java.io.Serializable;

public class XStreamSerializer extends SerializerBase {

    private XStream xStream;

    public XStreamSerializer() {
        xStream = new XStream(new PureJavaReflectionProvider());
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
