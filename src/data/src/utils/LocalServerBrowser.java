package utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LocalServerBrowser {

    private static String LOCAL_ADDR_PREFIX = "192.168.1.";

    public static List<String> scanForLocalServers(int expectedPort, Class serverInterface) {
        return IntStream.range(2,255)
                .parallel()
                .filter((addrEnd) -> {
            try {
                Connectable connectable = NetworkFactory.buildClient(serverInterface, new Object(), generateAddress(addrEnd), expectedPort);
                connectable.disconnect();
                return true;
            } catch (Exception ex) {
                return false;
            }
        })
                .mapToObj(LocalServerBrowser::generateAddress)
                .collect(Collectors.toList());
    }

    private static String generateAddress(int suffix) {
        return LOCAL_ADDR_PREFIX + suffix;
    }

}
