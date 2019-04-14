package state.action.collision;

import state.agent.IAgent;

public class MeleeOnCollision extends CollisionAction {

    IAgent baseAgent;

    public MeleeOnCollision(IAgent agent) {
        this.baseAgent = agent;
    }

    @Override
    public void execute(IAgent agent) {
        baseAgent.stop();
        agent.loseHealth(baseAgent.getAttackDamage());
    }
}
