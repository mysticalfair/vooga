package utils.network;

import java.io.Serializable;

public class INetRequest implements Serializable {

    private String[] args;
    private String method;

    public INetRequest(String method, String[] args) {
        this.method = method;
        this.args = args;
    }

    public String getMethod() {
        return method;
    }

    public String[] getArgs() {
        return args;
    }
}
