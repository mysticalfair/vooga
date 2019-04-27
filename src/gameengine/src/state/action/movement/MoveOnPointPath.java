package state.action.movement;

import state.agent.Agent;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.Map;

/**
 * Allows an agent to move on a pre-defined path.
 */
public class MoveOnPointPath extends MovementAction {

    private List<Point2D> points;
    private double speed;

    private int currPoint;

    private static final int TOLERANCE = 2;

    public MoveOnPointPath(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        points = (List<Point2D>) params.get("points");
        speed = (Double) params.get("speed");
        currPoint = 0;
    }

    /**
     * Move the baseAgent on a pre-defined path outlined by points.
     * @param agent The agent to move to.
     */
    @Override
    public void execute(Agent agent, double deltaTime) {
        if (distance(baseAgent.getX(), baseAgent.getY(), points.get(currPoint)) < TOLERANCE) {
            currPoint++;
        }

        Point2D p = points.get(currPoint);
        double angle = Math.arctan((p.getY() - baseAgent.getY()) / (p.getX() - baseAgent.getY()));
        double vx = speed * Math.cos(angle);
        double vy = speed * Math.sin(angle);
        baseAgent.setLocation(baseAgent.getX() + vx * deltaTime,
                              baseAgent.getY() + vy * deltaTime);
    }

    private double distance(double x1, double y1, Point2D point2) {
        return Math.sqrt(Math.pow(point2.getX() - x1, 2) +
                         Math.pow(point2.getY() - y1, 2));
    }
}
