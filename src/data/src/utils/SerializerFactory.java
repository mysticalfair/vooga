package utils;

import utils.serializers.XStreamSerializer;

public class SerializerFactory {

    private static XStreamSerializer xStreamSerializer;

    public static Serializer getXMLInstance() {
        if (xStreamSerializer == null) {
            xStreamSerializer = new XStreamSerializer();
        }
        return xStreamSerializer;
    }

}
