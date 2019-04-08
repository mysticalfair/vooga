package state.agent.agents.healthagents.units;

import state.action.movement.MoveStraightToAgent;
import state.condition.ClosestEnemy;

/**
 * Agent to represent a melee attacking agent that moves towards the nearest enemy
 * @author Jorge Raad
 */
public class ToNearestEnemyMeleeAgent extends MeleeAgent {

    public ToNearestEnemyMeleeAgent(double attackInterval, int attackRange, int damage, int speed) {
        // MeleeAgent takes care of attacking logic
        super(attackInterval, attackRange, damage);
        // add new ClosestEnemy Action Decision which passes the closest enemy to MoveStraightToAgent
        actionDecisions.add(new ClosestEnemy(this, new MoveStraightToAgent(this, speed)));
    }
}
