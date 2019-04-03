package utils.serializers;

import utils.SerializationException;
import utils.Serializer;

import java.io.*;

public abstract class SerializerBase implements Serializer {

    protected static final String SERIALIZATION_ERR = "Object could not be serialized due to ";
    protected static final String DESERIALIZATION_ERR = "Object could not be deserialized due to ";

    @Override
    public void save(Serializable state, File fileLocation) throws SerializationException, IOException {
        String json = serialize(state);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
        writer.write(json);
        writer.close();
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
}
