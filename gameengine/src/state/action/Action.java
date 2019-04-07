package state.action;

import state.agent.IAgent;

/**
 * An abstract class to define the common functionality of Actions.
 * @author Jamie Palka
 * @author David Miron
 */
public abstract class Action implements IAction {

    public abstract void execute(IAgent agent);

}
