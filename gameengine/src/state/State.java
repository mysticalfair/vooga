package state;

import java.util.ArrayList;
import java.util.List;
 /**
 * @Author:Luke_Truitt
 */
public class State implements IStateComplete {

    private List<IAgentComplete> agentsOptions;
    private List<IAgentComplete> agentsCurrent;
    private List<IObjective> objectivesCurrent;
    private Map<IAttributes> attributesCurrent;
    @Override
    public List<IAgent> getImmutableAgents() {
        List<IAgent> immutableAgentsCurrent = new ArrayList<>();
        for ( IAgentComplete agent : this.agentsCurrent ) {
            immutableAgentsCurrent.add(agent.toImmutable());
        }
        return immutableAgentsCurrent;
    }

    @Override
    public List<IAgentComplete> getMutableAgents() {
        return this.agentsCurrent;
    }

    @Override
    public List<IObjective> getObjectives() {
        return null; // TODO
    }
}
