package state.previous_to_remove.agents.healthagents.towers;

import state.action.spawn.SpawnAgentInitialDirection;
import state.previous_to_remove.ClosestAgentWithinRangeInterval;
import state.previous_to_remove.agents.nohealthagents.projectiles.ProjectileAgent;

public class ProjectileTower extends TowerAgent {

    public ProjectileTower(double shootingInterval, int shootingRange, ProjectileAgent projectile) {
        super();
        this.actionDecisions.add(
                new ClosestAgentWithinRangeInterval(this,
                                                    shootingInterval,
                                                    shootingRange,
                                                    new SpawnAgentInitialDirection(this, projectile)));
    }

}