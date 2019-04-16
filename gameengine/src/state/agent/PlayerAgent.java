package state.agent;

import state.Property;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class PlayerAgent implements IPlayerAgent {
    private double x;
    private double y;
    private String imageURL;
    private String name;
    private double direction;
    private int width;
    private int height;
    private List<Property> properties;

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

    public List<Property> getProperties() {return this.properties; }

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

    public void setProperty(String name, Object value) {

        for(Property property : this.properties) {
            if(property.getName().equals(name)) {
                var oldVal = property.getValue();
                property.setValue(value);
                pcs.firePropertyChange(name, oldVal, value);
            }
        }

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

}
