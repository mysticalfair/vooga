package authoring;

import state.AgentReference;
import state.Property;

import java.awt.geom.Point2D;
import java.util.List;

/**
 * Interface to define all the parts of a level of the game
 * @author David Miron
 */
public interface ILevelDefinition {

    /**
     * Get the agents that actually exist in the game
     * @return The currently existing agents
     */
    List<AgentReference> getCurrentAgents();
    void removeAgent(int index);
    void addAgent(String agentName, int x, int y, double direction, List<Property> instanceProperties);

    List<String> getPlaceableAgents();
    void removePlaceableAgent(int index);
    void removePlaceableAgent(String agentName);
    void addPlaceableAgent(String agentName);

    List<List<Point2D>> getPaths();
    void removePath(int index);
    void addPath(List<Point2D> path);
}
