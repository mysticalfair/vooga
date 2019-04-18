package state.action.spawn;

import state.IRequiresBaseAgent;
import state.agent.Agent;
import state.agent.AgentUtils;
import state.agent.Agent;

import java.util.Map;

/**
 * Action to spawn an agent, at the location of a base agent, with a direction pointing to the agent given in the execute method.
 * @author David Miron
 * @author Jamie Palka
 * @author Jorge Raad
 */
public class SpawnAgentInitialDirection extends SpawnAgent implements IRequiresBaseAgent {

    private Agent spawnAgent;
    private Agent baseAgent;

    /**
     * Create a SpawnAgentInitialDirection.
     */
    public SpawnAgentInitialDirection(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.spawnAgent = (Agent)params.get("agent");
    }

    @Override
    public void injectBaseAgent(Agent agent) {
        this.baseAgent = agent;
    }

    /**
     * Spawn the spawnAgent at the location of the baseAgent. Uses clone method, which allows the new agent to take on all
     * properties of the pre-defined spawnAgent that an instantiation of a SpawnAgentInitialDirection action will own.
     * @param agent The spawnAgent will be given a movement with the destination of this agent.
     */
    @Override
    public void execute(Agent agent, double deltaTime) throws CloneNotSupportedException {
        Agent newAgent = spawnAgent.clone();
        newAgent.setLocation(baseAgent.getX(), baseAgent.getY());
        newAgent.setDirection(AgentUtils.getAngleBetween(baseAgent, agent));
        newAgent.setLocation(baseAgent.getX(), baseAgent.getY());
        spawnAgent(newAgent);
        System.out.println("FIRE!");
    }

}
