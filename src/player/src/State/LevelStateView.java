package State;

import state.IPlayerLevelState;
import state.agent.Agent;
import state.agent.IPlayerAgent;
import state.attribute.IAttribute;
import state.objective.IPlayerObjective;
import state.objective.Objective;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class LevelStateView implements PropertyChangeListener {
    private List<IPlayerAgent> agentsOptions;
    private List<IPlayerAgent> agentsCurrent;

    public LevelStateView(IPlayerLevelState playerLevelState){
        super();
        this.init(playerLevelState);
        playerLevelState.addPropertyChangeListener(this);
    }

    public void init(IPlayerLevelState playerLevelState){
        this.agentsCurrent = playerLevelState.getImmutableAgents();
        this.agentsOptions = playerLevelState.getImmutableOptions();
    }

    public void propertyChange(PropertyChangeEvent e) {
        // TODO: Reflection to add or remove agents
    }
}
