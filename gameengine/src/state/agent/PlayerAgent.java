package state.agent;

import state.Property;
import utils.Serializer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.List;

public class PlayerAgent implements IPlayerAgent, Serializable, Cloneable {
    private int id;
    private double x;
    private double y;
    private String imageURL;
    private String name;
    private double direction;
    private int width;
    private int height;
    private List<Property> properties;

    private PropertyChangeSupport pcs;

    public PlayerAgent(int id, int x, int y, int width, int height, String name, double direction, String imageURL) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.direction = direction;
        this.imageURL = imageURL;
        this.name = name;
        pcs = new PropertyChangeSupport(this);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public String getImageURL() {
        return imageURL;
    }
    public String getName() {
        return name;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public double getDirection() {
        return direction;
    }

    public List<Property> getProperties() { return this.properties; }

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

    public void setHeight(int height) {
        var oldHeight = this.height;
        this.height = height;
        pcs.firePropertyChange("height", oldHeight, height);
    }

    public void setWidth(int width) {
        var oldWidth = this.width;
        this.width = width;
        pcs.firePropertyChange("width", oldWidth, width);
    }

    public void setDirection(double direction){
        var oldDir = this.direction;
        this.direction = direction;
        pcs.firePropertyChange("direction", oldDir, direction);
    }

    @Deprecated
    public void setProperty(String name, Object value) {

        for(Property property : this.properties) {
            if(property.getName().equals(name)) {
                var oldVal = property.getValue();
                property.setValue(value);
                pcs.firePropertyChange(name, oldVal, value);
            }
        }
    }

    public void setProperty(Property newProperty){
        for(Property p : this.properties) {
            if(p.getName().equals(newProperty.getName())) {
                var oldVal = p.getValue();
                p.setValue(newProperty.getValue());
                pcs.firePropertyChange(name, oldVal, newProperty.getValue());
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    @Override
    public PlayerAgent clone() throws CloneNotSupportedException {
        PlayerAgent clone = (PlayerAgent) super.clone();
        return clone;
    }
}
