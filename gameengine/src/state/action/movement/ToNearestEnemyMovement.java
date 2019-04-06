package state.action.movement;

import state.agent.IAgent;

public class ToNearestEnemyMovement extends MovementAction {

    public ToNearestEnemyMovement(IAgent baseAgent, int speed) {
        this.speed = speed;
    }

}
