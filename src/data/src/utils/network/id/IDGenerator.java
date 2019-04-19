package utils.network.id;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Singleton class for generating a unique ID for each request sent.
 * @author Jake Mullett
 */
public class IDGenerator {

    private static AtomicLong idCounter = new AtomicLong();

    private IDGenerator() {
        // private constructor so cannot be instantiated
    }

    public static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
