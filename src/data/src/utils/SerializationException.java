package utils;

/**
 * Exception thrown when an exception is run into during serialization/deserialization.
 * @author Jake Mullett
 */
public class SerializationException extends Exception {

    public SerializationException(String message) {
        super(message);
    }

}
