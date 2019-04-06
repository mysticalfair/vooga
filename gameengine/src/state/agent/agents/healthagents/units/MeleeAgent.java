package state.agent.agents.healthagents.units;

import state.action.ApplyAgentOperationAction;
import state.actiondecision.ClosestAgentWithinRangeInterval;
import state.agent.agents.healthagents.HealthAgent;
import state.agentoperation.DamageOperation;

/**
 * Agent to represent a melee attacking agent
 * @author David Miron
 */
public class MeleeAgent extends HealthAgent {

    public MeleeAgent(double attackInterval, int attackRange, int damage) {
        this.actionDecisions.add(
                new ClosestAgentWithinRangeInterval(this,
                                                    attackInterval,
                                                    attackRange,
                                                    new ApplyAgentOperationAction(
                                                            new DamageOperation(damage)
                                                    ))
        );
    }

}
