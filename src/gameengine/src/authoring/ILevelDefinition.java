package authoring;

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
    List<? extends IAgentDefinition> getCurrentAgents();
    void removeAgent(int index);
    void addAgent(String agentName, int x, int y, double direction);

    List<? extends IAgentDefinition> getPlaceableAgents();
    void removePlaceableAgent(int index);
    void removePlaceableAgent(String agentName);
    void addPlaceableAgent(String agentName);
}
