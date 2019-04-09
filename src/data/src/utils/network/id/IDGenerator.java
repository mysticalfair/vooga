package utils.network.id;

import java.util.concurrent.atomic.AtomicLong;

public class IDGenerator {

    private static AtomicLong idCounter = new AtomicLong();

    public static String createID()
    {
        return String.valueOf(idCounter.getAndIncrement());
    }
}
