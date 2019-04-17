package state.action.movement;

import state.agent.Agent;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

/**
 * Allows an agent to move on a pre-defined path.
 */
public class MoveOnPointPath extends MovementAction {

    public MoveOnPointPath(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        // TODO Paths?
    }

    /**
     * Move the baseAgent on a pre-defined path outlined by points.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(Agent agent, double deltaTime) {

    }
}
