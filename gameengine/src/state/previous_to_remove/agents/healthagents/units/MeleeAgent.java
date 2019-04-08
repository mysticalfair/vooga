package state.previous_to_remove.agents.healthagents.units;

import state.action.ApplyAgentOperation;
import state.previous_to_remove.ClosestAgentWithinRangeInterval;
import state.previous_to_remove.agents.healthagents.HealthAgent;
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
                                                    new ApplyAgentOperation(
                                                            new DamageOperation(damage)
                                                    ))
        );
    }

}
