package gameengine;

import java.util.List;

/**
 * Interface to define all the parts of a level of the game
 * @author David Miron
 */
public interface ILevelDefinition {
    List<IAgentDefinition> getDefinedAgents();
    void removeDefinedAgent(int index);
    void addAgentDefinition(IAgentDefinition agent);
}
