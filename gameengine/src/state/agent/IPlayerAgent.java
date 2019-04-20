package state.agent;
import java.beans.PropertyChangeListener;

public interface IPlayerAgent {

    double getX();
    double getY();
    String getImageURL();
    String getName();
    int getWidth();
    int getHeight();
    double getDirection();
   void addPropertyChangeListener(PropertyChangeListener listener);
}
