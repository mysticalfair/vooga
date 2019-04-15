package utils;

public class NetworkException extends Exception {

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
