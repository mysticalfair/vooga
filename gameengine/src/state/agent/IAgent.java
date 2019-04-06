package state.agent;

import java.awt.*;
import java.util.List;

/**
 * @author Jorge Raad
 * @Author:Luke_Truitt
 * @author David Miron
 * These are the extensions for the full agent. The one authoring and engine need.
 */
public interface IAgent {
    void update(List<IAgent> agents);

    /**
     * Returns the location of the Agent.
     * @return Point containing location of the Agent
     */
    Point getLocation();

    /**
     * Returns the team of the Agent.
     * @return String containing team of the Agent
     */
    String getTeam();

    /**
     * Returns the distance between this Agent and the given Agent
     * @param agent Agent to which the distance will be calculated
     * @return distance
     */
    double calculateDistance(IAgent agent);
}
