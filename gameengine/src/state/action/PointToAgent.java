package state.action;

import state.agent.Agent;
import state.agent.IAgent;
import state.condition.BaseAgentCondition;

/**
 * Action that changes the direction of the agent to face the given Agent.
 * @author Jorge Raad
 */
public class PointToAgent extends BaseAgentAction{
    @Override
    public void execute(IAgent agent) throws CloneNotSupportedException {
        double aDirection = baseAgent.getDirection();
        int[] aPosition = baseAgent.getLocation();
        int aX = aPosition[0];
        int aY = aPosition[1];
        int[] bPosition = agent.getLocation();
        int bX = bPosition[0];
        int bY = bPosition[1];
        double angleBetween = 180 / Math.PI * Math.asin((bY - aY)/(bX - aY));
    }

    public static void main(String[] args){
        int aX = 0;
        int aY = 0;
        int bX = 1;
        int bY = 1;
        System.out.println(180 / Math.PI * Math.atan((bY - aY)/(bX - aX)));
    }
}
