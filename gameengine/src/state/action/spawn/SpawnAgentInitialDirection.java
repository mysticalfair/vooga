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
    public void execute(IAgent agent) throws CloneNotSupportedException {

        Agent newAgent = spawnAgent.clone();

        /**
         * TODO - when an agent is spawned, its different types of ActionDecisions with embedded Actions
         *  (including all its possible movement types will be pre-defined.
         *  Depending on the type of baseAgent, the spawnAgent will need to set the destination for a specific one of its ActionDecisions.
         *  Need to decide on a way to implement this/store this information beforehand.
         *  Once figure that out, add a new method in IAgent to access the ActionDecisionList.
         *  Concepts to think about:
         *          - when spawn it, how does it know which ActionDecision to use? Ex: if it has 2 always ActionDecisions.
         *          - Assuming each movement action's destination/direction is defined by an agent.
         *          - Assuming speed of each agent type is pre-defined somewhere.
         *          - Need the SpawnAgent abstract class?
         */
    }
}
