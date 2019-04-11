package state.action;

import state.agent.IAgent;

import java.awt.geom.Point2D;

/**
 * An abstract class to define the common functionality of Actions.
 * @author Jamie Palka
 * @author David Miron
 */
public abstract class Action implements IAction {

    /**
     * @param agent
     * For some movements, the baseAgent's destination/direction is defined by the agent parameter.
     * For other movements, the agent parameter is ignored and the point passed into the constructor is used.
     * For example, the movements defined within collisions.
     * For all other actions, the baseAgent's action is directed toward the agent parameter.
     * For example, an agent spawned will go in the direction of the agent parameter or
     * a meleee action will be executed on the agent parameter.
     */
    public abstract void execute(IAgent agent) throws CloneNotSupportedException;


    // TODO assumption in comment correct?

}
