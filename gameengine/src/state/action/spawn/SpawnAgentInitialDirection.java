package state.action.spawn;

import state.agent.Agent;
import state.agent.IAgent;

/**
 * Action to spawn an agent, at the location of a base agent, with a direction pointing to the
 * agent given in the execute method
 * @author David Miron
 * @author Jamie Palka
 * @author Jorge Raad
 */
public class SpawnAgentInitialDirection extends SpawnAgent {

    private Agent spawnAgent;

    /**
     * Create a SpawnAgentInitialDirection
     * @param baseAgent The spawn agent is created at the location of this agent
     * @param spawnAgent The agent to spawn
     */
    public SpawnAgentInitialDirection(Agent baseAgent, Agent spawnAgent) {
        this.spawnAgent = spawnAgent;
    }

    /**
     * Spawn the agent given in the constructor, at the location of the agent passed to this method
     * @param agent The spawn agent will be given a direction that points towards this agent
     */
    @Override
    public void execute(IAgent agent) {

        // TODO Set initial velocity of agent and call spawnAgent
        /*

        Agent newAgent = this.agent.clone();
        newAgent.setDirection(   agent.getPosition()   );

        or something like this

         */
        Agent agentWithVelocity = null;
        spawnAgent(agentWithVelocity);
    }
}
