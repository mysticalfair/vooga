package state.objective;

import state.State;
import state.agent.Agent;
import state.attribute.Attribute;
import state.attribute.IAttribute;

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

    public static Attribute getAttributeFromName(State state, String attributeName) {

        for(IAttribute attribute : state.getCurrentAttributes()) {
            if (attribute.getName().equals(attributeName)) {
                return (Attribute) attribute;
            }
        }
        return null;
    }
}
