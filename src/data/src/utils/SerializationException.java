package utils;

/**
 * Exception thrown when an exception is run into during serialization/deserialization.
 */
public class SerializationException extends Exception {

    public SerializationException(String message) {
        super(message);
    }

}
