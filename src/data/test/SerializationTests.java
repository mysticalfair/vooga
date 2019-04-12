import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Serializer;
import utils.SerializerSingleton;

public class SerializationTests {

    private Serializer serializer;

    @BeforeAll
    public void setup() {
        serializer = SerializerSingleton.getXMLInstance();
    }

    @Test
    public void testSerialization() {

    }
}
