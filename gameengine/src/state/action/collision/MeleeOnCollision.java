package state.action.collision;

import state.agent.Agent;

public class MeleeOnCollision extends CollisionAction {

    Agent baseAgent;

    public MeleeOnCollision(Agent agent) {
        this.baseAgent = agent;
    }

    @Override
    public void execute(Agent agent) {
        baseAgent.stop();
        agent.loseHealth(baseAgent.getAttackDamage());
    }
}
