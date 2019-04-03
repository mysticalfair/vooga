package state.Agent;

import java.awt.*;

/**
 * @Author:Luke_Truitt
 * This is the list of stuff that the player will have access to.
 */
public interface IAgent {

    Point getXY();
    String getImageURL();
    Boolean getIsAttacking();
    int getHealth();

}
