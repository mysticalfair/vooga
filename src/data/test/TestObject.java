/**
 * This interface and the TestObject exist here for unit testing, as integration testing
 * in this package would add a dependency on the gameengine module.
 */
public class TestObject implements BasicTestInterface {

    private String string;
    private String[] args;

    public TestObject() {
        string = "";
        args = new String[0];
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public void changeString(String string) {
        this.string = string;
    }

    @Override
    public void storeArgs(String... args) {
        this.args = args;
    }

    @Override
    public String getString() {
        return string;
    }
}
