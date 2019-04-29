package utils.serializers;

import utils.SerializationException;
import utils.Serializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * SerializerBase class which has closed implementations of save and load.
 * This does not limit the serialization/deserialization implementations, they simply just need to implement
 * serialize() and deserialize().
 * @author Jake Mullett
 */
public abstract class SerializerBase implements Serializer {

    protected static final String SERIALIZATION_ERR = "Object could not be serialized due to ";
    protected static final String DESERIALIZATION_ERR = "Object could not be deserialized due to ";

    @Override
    public final void save(Serializable state, File fileLocation) throws SerializationException{
        String json = serialize(state);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
            writer.write(json);
            writer.close();
        } catch (IOException ex) {
            throw new SerializationException(SERIALIZATION_ERR, ex);
        }
    }

    @Override
    public void save(Serializable state, File fileLocation, List<String> resourcesToMove) throws SerializationException {
        save(state, fileLocation);
        try {
            moveResources(fileLocation.getAbsolutePath(), resourcesToMove);
        } catch (IOException ex) {
            throw new SerializationException(SERIALIZATION_ERR, ex);
        }
    }

    @Override
    public final Object load(File fileLocation) throws SerializationException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
        StringBuilder builder = new StringBuilder();
        String curline = reader.readLine();
        while (curline != null) {
            builder.append(curline);
            curline = reader.readLine();
        }
        String json = builder.toString();
        return deserialize(json);
    }

    private void moveResources(String baseFilePath, List<String> resources) throws IOException {
        String resourcesFolderName = "resources";
        String folderFileName = baseFilePath + resourcesFolderName;
        File folderFile = new File(folderFileName);
        if (!folderFile.exists()) {
            folderFile.mkdir();
        }
        for (String resourcePath : resources) {
            File resourceToCopy = new File(resourcePath);
            File targetResourceDest = new File(folderFileName + "/" + resourceToCopy.getName());
            Files.copy(resourceToCopy.toPath(), targetResourceDest.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
