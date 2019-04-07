package state.action.spawn;

import state.agent.Agent;
import state.agent.IAgent;

import java.awt.*;

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
     * Spawn the spawnAgent at the location of the baseAgent.
     * @param agent The spawnAgent will be given a direction that points towards this agent.
     */
    @Override
    public void execute(IAgent agent) {

        double xDisplacement = (agent.getLocation().getX() - baseAgent.getLocation().getX());
        double yDisplacement = (agent.getLocation().getY() - baseAgent.getLocation().getY());
        Point movement = new Point(xDisplacement, yDisplacement);
        baseAgent.move(movement);
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
