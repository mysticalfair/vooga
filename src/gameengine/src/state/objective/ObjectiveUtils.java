package state.objective;

import state.State;
import state.agent.Agent;

public class ObjectiveUtils {

    //name of objective OBJECTIVE_IDENTIFICATION_PROPERTY in an Agent's properties list
    public static final String OBJECTIVE_IDENTIFICATION_PROPERTY = "objectiveID";

    public static Agent getAgentFromObjectiveIdentificationPropertyValue(State state, String objectiveIdentificationPropertyValue) {
        Agent significantAgent = null;
        for(Agent agent : state.getCurrentAgents()) {
            if(((Comparable) agent.getPropertyValue(OBJECTIVE_IDENTIFICATION_PROPERTY)).compareTo(objectiveIdentificationPropertyValue) == 0) {
                significantAgent = agent;
            }
        }
        return significantAgent;
    }
}
