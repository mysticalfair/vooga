package utils;

import utils.gson.GsonSerializer;

public class SerializerFactory {

    public static Serializer buildInstance() {
        return new GsonSerializer();
    }
}
