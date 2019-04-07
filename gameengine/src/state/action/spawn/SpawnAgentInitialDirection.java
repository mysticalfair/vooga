package state.action.spawn;

import state.agent.Agent;
import state.agent.IAgent;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Action to spawn an agent, at the location of a base agent, with a direction pointing to the agent given in the execute method.
 * @author David Miron
 * @author Jamie Palka
 * @author Jorge Raad
 */
public class SpawnAgentInitialDirection extends SpawnAgent {

    private Agent spawnAgent = new Agent()
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
     * Spawn the spawnAgent at the location of the baseAgent.
     * @param agent The spawnAgent will be given a direction that points towards this agent.
     */
    @Override
    public void execute(IAgent agent) {

        // TODO create a new action decision and movement action and assign it to new agent creating. call spawnAgent to do so



        /*

        Agent newAgent = this.agent.clone();
        newAgent.setDirection(   agent.getPosition()   );

        or something like this

         */
        Agent agentWithVelocity = null;
        spawnAgent(agentWithVelocity);
    }
}
