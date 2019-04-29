package state.action.spawn;

import authoring.exception.PropertyDoesNotExistException;
import state.AgentReference;
import state.IRequiresBaseAgent;
import state.Property;
import state.agent.Agent;

import java.util.List;
import java.util.Map;

public class SpawnAgentInjectProperties extends SpawnAgent implements IRequiresBaseAgent {

    private String spawnAgentName;
    private List<Property> injectionProperties;

    private Agent baseAgent;

    public SpawnAgentInjectProperties(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.spawnAgentName = (String) params.get("agent");
        this.injectionProperties = (List<Property>) params.get("properties");
    }

    @Override
    public void execute(Agent agent, double deltaTime) throws CloneNotSupportedException, PropertyDoesNotExistException {
        spawnAgent(new AgentReference(spawnAgentName, baseAgent.getX(), baseAgent.getY(),
                                      baseAgent.getDirection(), injectionProperties));
    }

    @Override
    public void injectBaseAgent(Agent agent) {
        this.baseAgent = agent;
    }
}
