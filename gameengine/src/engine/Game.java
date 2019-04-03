package engine;

import state.IAgent;
import state.IObjective;
import state.State;

import java.util.List;

public class Game {

    private State state;

    private void step() {

        for (IAgent agent: state.getAgents())
            agent.update(state.getAgents());

        for (IObjective objective: state.getObjectives())
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
