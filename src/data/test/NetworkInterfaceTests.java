import org.junit.jupiter.api.*;
import utils.NetworkFactory;
import utils.NetworkedClientInterface;
import utils.NetworkedServerInterface;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NetworkInterfaceTests {

    private NetworkedServerInterface serverInterface;
    private NetworkedClientInterface clientInterface;
    private BasicTestInterface iface;

    @BeforeAll
    public void setUp() throws IOException {
        iface = new TestObject();
        serverInterface = NetworkFactory.buildServer(BasicTestInterface.class, iface, 1234);
        clientInterface = NetworkFactory.buildClient(BasicTestInterface.class, this, "127.0.0.1", 1234);
    }

    @Test
    public void testChangeString() throws InterruptedException{
        String newString = "newString";
        assertEquals(iface.getString(), "");
        var client = (BasicTestInterface & NetworkedClientInterface) clientInterface;
        client.changeString(newString);
        Thread.sleep(50);
        assertEquals(iface.getString(), newString);
    }

    @Test
    public void testChangeArgs() throws InterruptedException{
        String[] args = {"arg1", "arg2"};
        assertEquals(iface.getArgs().length, 0);
        var client = (BasicTestInterface & NetworkedClientInterface) clientInterface;
        client.storeArgs(args);
        Thread.sleep(50);
        assertEquals(2, iface.getArgs().length);
        client.storeArgs("arg0", "arg1", "arg2", "arg3");
        Thread.sleep(50);
        assertEquals(4, iface.getArgs().length);
    }

    @Test
    public void testArgTypes() throws InterruptedException {
        var client = (BasicTestInterface & NetworkedClientInterface) clientInterface;
        client.storeObjects(0, "String", 0.0, new Date());
        Thread.sleep(50);
        Object[] returned = iface.getObjects();
        assertEquals((Integer)0, returned[0]);
        assertEquals("String", returned[1]);
        assertEquals(0.0, returned[2]);
        assertEquals(Date.class, returned[3].getClass());
    }

    @Test
    public void testMethodWithReturnType() {
        var client = (BasicTestInterface & NetworkedClientInterface) clientInterface;
        long curtime = System.currentTimeMillis();
        client.getString();
        System.out.println("Time to get a string: " + (System.currentTimeMillis()-curtime));
        assertEquals(iface.getString(), client.getString());
    }

    public static void main(String[] args) {
        // do nothing. This exists so that we can build a JAR for XStream.
    }

}
