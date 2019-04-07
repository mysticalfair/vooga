package state.action;

import state.agent.IAgent;

/**
 * Defines an Action to be executed by an ActionDecision. An Action defines all types of activity, including movement.
 * @author Jamie Palka
 * @author David Miron
 */
public interface IAction {

    void execute(IAgent agent) throws CloneNotSupportedException;

}
