package utils.serializers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.SerializationException;
import utils.Serializer;

import java.io.Serializable;

public class GsonSerializer extends SerializerBase implements Serializer {

    private Gson gson;

    public GsonSerializer() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public Serializable deserialize(String object, Class<? extends Serializable> objectType) throws SerializationException {
        try {
            return gson.fromJson(object, objectType);
        } catch (Exception exception) {
            throw new SerializationException(DESERIALIZATION_ERR + exception.getMessage());
        }
    }

    @Override
    public String serialize(Serializable object) throws SerializationException {
        try {
            return gson.toJson(object);
        } catch (Exception exception) {
            throw new SerializationException(SERIALIZATION_ERR + exception.getMessage());
        }
    }

}
