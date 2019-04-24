package state.objective;

import state.State;
import state.agent.Agent;

/**
 * @author Jamie Palka
 * Class to define the functionality of removing an agent.
 */
public class RemoveAgentOutcome implements IObjectiveOutcome {

    Agent agent;

    public RemoveAgentOutcome(Agent agent) {
        this.agent = agent;
    }

    public void execute(State state) {

    }

}
