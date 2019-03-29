package utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.SerializationException;
import utils.Serializer;

import java.io.*;

public class GsonSerializer implements Serializer {

    private Gson gson;

    public GsonSerializer() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public Serializable deserialize(String object, Class<? extends Serializable> objectType) throws SerializationException {
        try {
            return gson.fromJson(object, objectType);
        } catch (Exception exception) {
            throw new SerializationException("Object could not be deserialized due to " + exception.getMessage());
        }
    }

    @Override
    public Serializable load(File fileLocation, Class<? extends Serializable> objectType) throws SerializationException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
        StringBuilder builder = new StringBuilder();
        String curline = reader.readLine();
        while (curline != null) {
            builder.append(curline);
            curline = reader.readLine();
        }
        String json = builder.toString();
        return deserialize(json, objectType);
    }

    @Override
    public String serialize(Serializable object) throws SerializationException {
        try {
            return gson.toJson(object);
        } catch (Exception exception) {
            throw new SerializationException("Object could not be serialized due to " + exception.getMessage());
        }
    }

    @Override
    public void save(Serializable state, File fileLocation) throws SerializationException, IOException {
        String json = serialize(state);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
        writer.write(json);
        writer.close();
    }

}
