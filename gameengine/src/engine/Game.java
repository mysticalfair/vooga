package engine;

import state.Agent.IAgent;
import state.Objective.IObjective;
import state.State;

/**
 * @Author:Luke_Truitt
 * @author David Miron
 * @author Jamie Palka
 */
public class Game {

    private State state;

    private void step() {

        for (IAgent agent: state.getMutableAgents())
            agent.update(state.getMutableAgents());

        for (IObjective objective: state.getMutableObjectives())
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
