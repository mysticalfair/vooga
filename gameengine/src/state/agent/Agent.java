package state.agent;

import state.actiondecision.ActionDecision;
import state.action.movement.MovementAction;

import java.awt.Point;
import java.util.List;

/**
 * @author David Miron
 * @author Luke_Truitt
 * Agent used by backend and authoring
 */
public class Agent implements IAgent {

    private int id;
    private Point location;
    private String imageURL;

    protected MovementAction movementBehavior;
    protected List<ActionDecision> actionDecisions;

    /**
     * Update the agent's state, by moving and executing action decisions
     * @param agents All other agents in play
     */
    @Override
    public void update(List<IAgent> agents) {

        for (ActionDecision decision: actionDecisions)
            decision.execute(agents);

    }

    /**
     * Move the current agent a specified distance
     * @param movement The vector representing the movement
     */
    private void move(Point movement) {
        this.location.translate(movement.x, movement.y);
    }


}
