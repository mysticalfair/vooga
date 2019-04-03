package state;

import java.util.List;

/**
 * @author David Miron
 * @author Jamie Palka
 */
public class State implements IStateAuthoring, IStatePlayer {

    private List<IAgent> agentsOptions;
    private List<IAgent> agentsCurrent;
    private List<IObjective> objectivesCurrent;

    @Override
    public List<IAgent> getAgents() {
        return null; // TODO
    }

    @Override
    public List<IObjective> getObjectives() {
        return null; // TODO
    }
}
