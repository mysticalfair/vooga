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
    private String team;

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
    public void move(Point movement) {
        this.location.translate(movement.x, movement.y);
    }

    /**
     * Returns the location of the Agent.
     * @return Point containing location of the Agent
     */
    public Point getLocation(){
        return location;
    }

    /**
     * Returns the team of the Agent.
     * @return String containing team of the Agent
     */
    public String getTeam(){
        return team;
    }

    /**
     * Returns the distance between this Agent and the given Agent
     * @param agent Agent to which the distance will be calculated
     * @return distance
     */
    public double calculateDistance(IAgent agent){
        return location.distance(agent.getLocation());
    }



}
