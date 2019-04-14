package gameengine;

import java.util.List;

/**
 * Interface to define all the parts of a level of the game
 * @author David Miron
 */
public interface ILevelDefinition {

    /**
     * Get the agents that are defined to exist
     * @return The defined agents
     */
    List<? extends IAgentDefinition> getDefinedAgents();
    void removeDefinedAgent(int index);
    void addIAgentDefinition(IAgentDefinition agent);

    /**
     * Get the agents that actually exist in the game
     * @return The currently existing agents
     */
    List<? extends IAgentDefinition> getCurrentAgents();
    void removeAgent(int index);
    void addAgent(IAgentDefinition agent);
}
