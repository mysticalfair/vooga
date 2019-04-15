package state.agent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

public class PlayerAgent implements IPlayerAgent {
    private double x;
    private double y;
    private String imageURL;
    private String team;
    private String name;
    private int health;
    private double direction;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getImageURL() {
        return imageURL;
    }
    public String getTeam() {
        return team;
    }
    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public double getDirection() {
        return direction;
    }
    public void setImageURL(String url) {
        var oldUrl = this.imageURL;
        this.imageURL = url;
        pcs.firePropertyChange("imageURL", oldUrl, url);
    }

    public void setName(String name) {
        var oldName = this.name;
        this.name = name;
        pcs.firePropertyChange("name", oldName, name);
    }
    public void setX(double x){
        var oldX = this.x;
        this.x = x;
        pcs.firePropertyChange("x", oldX, x);
    }
    public void setY(double y){
        var oldY = this.y;
        this.y = y;
        pcs.firePropertyChange("y", oldY, y);
    }
    public void setHealth(int health) {
        var oldHealth = this.health;
        this.health = health;
        pcs.firePropertyChange("health", oldHealth, health);
    }
    public void setTeam(String team) {
        var oldTeam = this.team;
        this.team = team;
        pcs.firePropertyChange("team", oldTeam, team);

    }
    public void setDirection(double direction){
        var oldDir = this.direction;
        this.direction = direction;
        pcs.firePropertyChange("direction", oldDir, direction);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
}
