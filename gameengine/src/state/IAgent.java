package state;

import java.awt.*;

public interface IAgent {

    Point getXY();
    String getImageURL();
    Boolean getIsAttacking();
    int getHealth();

}
