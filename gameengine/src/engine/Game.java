package engine;

import state.Agent.IAgentComplete;
import state.Objective.IObjectiveComplete;
import state.StateComplete;

/**
 * @Author:Luke_Truitt
 * @author David Miron
 * @author Jamie Palka
 */
public class Game {

    private StateComplete state;

    private void step() {

        for (IAgentComplete agent: state.getMutableAgents())
            agent.update(state.getMutableAgents());

        for (IObjectiveComplete objective: state.getMutableObjectives())
            objective.execute(state);

        sendState();

    }

    /**
     * Send the state to the Player
     */
    private void sendState() {
        // TODO
    }

}
