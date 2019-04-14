package state.action.collision;

import state.agent.IAgent;

public class ShooterOnCollision extends CollisionAction {
    IAgent baseAgent;

    public ShooterOnCollision(IAgent agent) {
        this.baseAgent = agent;
    }

    @Override
    public void execute(IAgent agent) {
        agent.loseHealth(baseAgent.getAttackDamage());
        System.out.println("Pew");
    }
}
