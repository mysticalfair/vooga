package state;

public class AgentReference {

    private String name;
    private double x;
    private double y;
    private double direction;

    public AgentReference(String name, double x, double y, double direction) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDirection() {
        return direction;
    }
}
