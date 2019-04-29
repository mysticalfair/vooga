package utils;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Set of interfaces for saving any Serializable object into a file or into a string, or reversing any serialization.
 * @author Jake Mullett
 */
public interface Serializer {

    /**
     * Takes in a Serializable object and a File to save it to and writes it.
     */
    public void save(Serializable state, File fileLocation) throws SerializationException;

    public void save(Serializable state, File fileLocation, List<String> resourcesToMove) throws SerializationException;

    /**
     * Takes in a Serializable object and returns the serialized string.
     */
    public String serialize(Serializable object) throws SerializationException;

    /**
     * Takes in a String and returns a Serializable object back.
     */
    public Object deserialize(String object) throws SerializationException;

    /**
     * Takes in a File and loads the serial object from this file.
     */
    public Object load(File fileLocation) throws SerializationException, IOException;

}