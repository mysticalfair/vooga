package state.action.spawn;

import state.agent.Agent;
import state.agent.AgentUtils;
import state.agent.Agent;

/**
 * Action to spawn an agent, at the location of a base agent, with a direction pointing to the agent given in the execute method.
 * @author David Miron
 * @author Jamie Palka
 * @author Jorge Raad
 */
public class SpawnAgentInitialDirection extends SpawnAgent {

    private Agent spawnAgent;
    private Agent baseAgent;

    /**
     * Create a SpawnAgentInitialDirection.
     * @param baseAgent The spawn agent is created at the location of this agent.
     * @param spawnAgent The agent to spawn.
     */
    public SpawnAgentInitialDirection(Agent baseAgent, Agent spawnAgent) {

        this.spawnAgent = spawnAgent;
        this.baseAgent = baseAgent;
    }

    /**
     * Spawn the spawnAgent at the location of the baseAgent. Uses clone method, which allows the new agent to take on all
     * properties of the pre-defined spawnAgent that an instantiation of a SpawnAgentInitialDirection action will own.
     * @param agent The spawnAgent will be given a movement with the destination of this agent.
     */
    @Override
    public void execute(Agent agent) throws CloneNotSupportedException {

        Agent newAgent = spawnAgent.clone();
        newAgent.setLocation(baseAgent.getX(), baseAgent.getY());
        AgentUtils.getAngleBetween(baseAgent, agent);
        spawnAgent(newAgent);
    }

}
