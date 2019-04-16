package state.condition;

import state.agent.Agent;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to return valid agents based on some timing interval
 * @author David Miron
 */
public class IntervalCondition extends Condition {

    private double intervalMillis;
    private long lastOccurrence;

    /**
     * Create an IntervalCondition
     * @param interval The interval, in seconds
     */
    public IntervalCondition(double interval) {
        this.intervalMillis = interval * 1000;
        this.lastOccurrence = 0;
    }

    /**
     * Return all agents if some interval has passed, or else return none
     * @param agents The agents to filter
     * @return All the agents if some interval has passed, or else an empty list
     */
    @Override
    public List<Agent> getValid(List<Agent> agents) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastOccurrence > intervalMillis) {
            lastOccurrence = currentTime;
            return agents;
        } else {
            return new ArrayList<>();
        }
    }
}
