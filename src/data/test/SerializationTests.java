import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Serializer;
import utils.SerializerFactory;

public class SerializationTests {

    private Serializer serializer;

    @BeforeAll
    public void setup() {
        serializer = SerializerFactory.getXMLInstance();
    }

    @Test
    public void testSerialization() {

    }
}
