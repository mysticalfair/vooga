package state.action;

import state.agent.IAgent;

/**
 * An abstract class to define the common functionality of Actions.
 * @author Jamie Palka
 * @author David Miron
 */
public abstract class Action implements IAction {


    /**
     * @param agent
     * For movements, the baseAgent's destination/direction is defined by the agent parameter.
     * For all other actions, the baseAgent's action is directied toward the agent parameter.
     * For example, an agent spawned will go in the direction of the agent parameter or
     * a meleee action will be executed on the agent parameter.
     */
    public abstract void execute(IAgent agent) throws CloneNotSupportedException;

    // TODO assumption in comment correct?

}
