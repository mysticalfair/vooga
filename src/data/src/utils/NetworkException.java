package utils;

public class NetworkException extends Exception {

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public NetworkException(String message, Object... args) {
        super(String.format(message, args));
    }

    public NetworkException(String message, Throwable throwable,  Object... args) {
        super(String.format(message, args), throwable);
    }

}
