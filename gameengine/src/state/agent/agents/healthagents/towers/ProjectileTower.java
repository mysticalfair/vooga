package state.agent.agents.healthagents.towers;

import state.action.spawn.SpawnAgentInitialDirection;
import state.actiondecision.ClosestAgentWithinRangeInterval;
import state.agent.agents.nohealthagents.projectiles.ProjectileAgent;

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