package utils;

import utils.gson.GsonSerializer;
import utils.gson.XStreamSerializer;

public class SerializerFactory {

    public static Serializer buildXMLInstance() {
        return new XStreamSerializer();
    }

    public static Serializer buildJSONInstance() {
        return new GsonSerializer();
    }
}
