package state.agent;

public class AgentUtils {

    /**
     * Get the angle between two agent1 and agent2, in reference to straight left, using unit circle notation
     * @param agent1 The first agent
     * @param agent2 The second agent
     * @return The angle between the two agents
     */
    public static double getAngleBetween(Agent agent1, Agent agent2) {
        double dx = agent2.getX() - agent1.getX();
        dx = dx == 0 ? 1 : dx;
        double dy = agent2.getY() - agent1.getY();
        return Math.atan(dy / dx) * 180 / Math.PI;
    }

}
