package state.action.collision;

import state.agent.IAgent;

public class MeleeOnCollision extends CollisionAction {

    IAgent baseAgent;

    @Override
    public void execute(IAgent agent) {
        baseAgent.stop();
        agent.loseHealth(baseAgent.getAttackDamage());
        System.out.println("Collided");
    }
}
