package state.action;

import authoring.IActionDefinition;
import engine.event.GameEventMaster;
import state.IRequiresGameEventMaster;
import state.agent.Agent;

import java.awt.geom.Point2D;
import java.io.Serializable;

/**
 * An abstract class to define the common functionality of Actions.
 * @author Jamie Palka
 * @author David Miron
 */
public abstract class Action implements IActionDefinition, IRequiresGameEventMaster, Serializable {

    protected GameEventMaster eventMaster;

    private String name;

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Inject the GameEventMaster to an action
     * @param eventMaster The Event Master
     */
    public void injectGameEventMaster(GameEventMaster eventMaster) {
        this.eventMaster = eventMaster;
    }

    /**
     * @param agent
     * For some movements, the baseAgent's destination/direction is defined by the agent parameter.
     * For other movements, the agent parameter is ignored and the point passed into the constructor is used.
     * For example, the movements defined within collisions.
     * For all other actions, the baseAgent's action is directed toward the agent parameter.
     * For example, an agent spawned will go in the direction of the agent parameter or
     * a meleee action will be executed on the agent parameter.
     */
    public abstract void execute(Agent agent, double deltaTime) throws CloneNotSupportedException;


    // TODO assumption in comment correct?

}
