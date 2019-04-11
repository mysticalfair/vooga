package utils;

import utils.serializers.XStreamSerializer;

/**
 * Singleton class for creating and passing a single XStream instance.
 * One instance is used to cut down on network response time with instantiating a Serialzer for each request.
 * @author Jake Mullett
 */
public class SerializerSingleton {

    private static XStreamSerializer xStreamSerializer;

    public static Serializer getXMLInstance() {
        if (xStreamSerializer == null) {
            buildXMLInstance();
        }
        return xStreamSerializer;
    }

    private static void buildXMLInstance() {
        xStreamSerializer = new XStreamSerializer();
    }

}
