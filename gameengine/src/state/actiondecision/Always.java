package state.actiondecision;

import state.agent.IAgent;

import java.util.List;

/**
 * ActionDecision that always decides to run its Action on its baseAgent.
 * Intended to be used primarily for movement.
 * @author Jorge Raad
 */
public class Always extends ActionDecision {
    @Override
    public void execute(List<IAgent> agents) {
        action.execute(baseAgent);
    }
}
