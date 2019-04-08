package state.agent.agents.nohealthagents.projectiles;

import state.action.ApplyAgentOperation;
import state.condition.OnEnemyCollision;
import state.agentoperation.DamageOperation;

/**
 * Class for projectile that moves in straight line and harms any enemy it hits.
 * @author Jorge Raad
 */
public class Arrow extends ProjectileAgent{

    public Arrow(String team, int damage) {
        // TODO : Set the team.
        //  This requires discussion of Agent constructors and possible factory that will be creating them.
        //  Also, how will direction be set by tower?
        this.actionDecisions.add(new OnEnemyCollision(this,
                new ApplyAgentOperation(new DamageOperation(damage))));
        this.actionDecisions.add(new Always(this, new StraightLineMovement()));
    }
}
