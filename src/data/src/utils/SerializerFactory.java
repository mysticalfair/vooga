package utils;

import utils.serializers.GsonSerializer;
import utils.serializers.XStreamSerializer;

public class SerializerFactory {

    public static Serializer buildXMLInstance() {
        return new XStreamSerializer();
    }

    public static Serializer buildJSONInstance() {
        return new GsonSerializer();
    }
}
