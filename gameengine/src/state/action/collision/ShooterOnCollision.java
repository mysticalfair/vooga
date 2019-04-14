package state.action.collision;

import state.agent.Agent;

public class ShooterOnCollision extends CollisionAction {
    Agent baseAgent;

    public ShooterOnCollision(Agent agent) {
        this.baseAgent = agent;
    }

    @Override
    public void execute(Agent agent) {
        agent.loseHealth(baseAgent.getAttackDamage());
        System.out.println("Pew");
    }
}
