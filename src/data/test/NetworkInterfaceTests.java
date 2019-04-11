import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.NetworkFactory;
import utils.NetworkedClientInterface;
import utils.NetworkedServerInterface;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NetworkInterfaceTests {

    private static final String LOCALHOST = "127.0.0.1";
    private static final int SERVER_PORT = 1234;
    private static final int SLEEP_TIME = 50;

    private NetworkedServerInterface serverInterface;
    private NetworkedClientInterface clientInterface;
    private BasicTestInterface interfaceViaClient;
    private BasicTestInterface iface;

    @BeforeAll
    public void setUp() throws IOException {
        iface = new TestObject();
        serverInterface = NetworkFactory.buildServer(BasicTestInterface.class, iface, SERVER_PORT);
        clientInterface = NetworkFactory.buildClient(BasicTestInterface.class, this, LOCALHOST, SERVER_PORT);
        interfaceViaClient = (BasicTestInterface) clientInterface;
    }

    @Test
    public void testChangeString() throws InterruptedException{
        String newString = "newString";
        runAndWaitForNetwork(() -> interfaceViaClient.changeString(newString));
        assertEquals(iface.getString(), newString);
    }

    @Test
    public void testChangeArgs() throws InterruptedException{
        String[] args = {"arg1", "arg2"};
        iface.storeArgs(null);
        assertEquals(null, iface.getArgs()); // sanity check
        runAndWaitForNetwork(() -> interfaceViaClient.storeArgs(args));
        assertArrayEquals(args, iface.getArgs());
    }

    @Test
    public void testArgTypes() throws InterruptedException {
        Object[] args = new Object[] {0, "String", 0.0, new Date()};
        runAndWaitForNetwork(() -> interfaceViaClient.storeObjects(args));
        assertArrayEquals(args, iface.getObjects());
    }

    @Test
    public void testMethodWithReturnType() {
        assertEquals(iface.getString(), interfaceViaClient.getString());
        runAndTime("get one string via network", () -> interfaceViaClient.getString());
    }

    @Test
    public void testMethodWithMultipleReturnObjects() {
        assertArrayEquals(iface.getObjects(), interfaceViaClient.getObjects());
        runAndTime("get array of arguments via network", () -> interfaceViaClient.getObjects());
    }

    @Test
    public void testMultipleClientsOneObject() throws IOException, InterruptedException {
        int newport = 1235;
        var server2 =  NetworkFactory.buildServer(BasicTestInterface.class, iface, newport);
        BasicTestInterface client2 = (BasicTestInterface)NetworkFactory.buildClient(BasicTestInterface.class, this, LOCALHOST, newport);
        runAndWaitForNetwork(()-> client2.storeArgs("john", "cena"));
        assertArrayEquals(iface.getArgs(), interfaceViaClient.getArgs());
    }

    @Test
    public void testMapInterface() throws IOException {
        int newport = 1238;
        Map<String, String> map = new HashMap<>();
        var server2 =  NetworkFactory.buildServer(BasicTestInterface.class, map, newport);
        Map<String, String> client2 = (Map<String, String>)NetworkFactory.buildClient(Map.class, this, LOCALHOST, newport);
        client2.put("t", "e");
        assertEquals(map.get("t"), client2.get("t"));
        runAndTime("Put an object into a hashmap", () -> client2.put("q", "e"));
    }

    private void runAndTime(String description, Runnable runnable) {
        long curtime = System.nanoTime();
        runnable.run();
        long timeafter = System.nanoTime();
        System.out.println("Time to " + description + ": " + (timeafter-curtime)/1000000.0 + "ms");
    }

    private void runAndWaitForNetwork(Runnable runnable) throws InterruptedException {
        runnable.run();
        Thread.sleep(SLEEP_TIME);
    }

    public static void main(String[] args) {
        // do nothing. This exists so that we can build a JAR for XStream.
    }

}
