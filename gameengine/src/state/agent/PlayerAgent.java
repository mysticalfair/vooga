package state.agent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Observable;

public class PlayerAgent implements IPlayerAgent {
    private int myX;
    private int myY;
    private String imageURL;
    private String team;
    private int health;
    private double direction;

    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public void addPropertyChangerListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }


}
