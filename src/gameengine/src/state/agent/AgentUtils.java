package state.agent;

import java.io.*;

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

    /**
     * This method makes a "deep clone" of any Java object it is given.
     * @author Alvin Alexander, http://alvinalexander.com
     */
    public static Object deepClone(Object object) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(object);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        return ois.readObject();
    }

}
