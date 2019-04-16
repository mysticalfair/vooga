package engine;

import engine.event.GameEventMaster;
import gameengine.IAgentDefinition;
import gameengine.ILevelDefinition;
import state.IRequiresGameEventMaster;
import state.LevelState;
import state.agent.Agent;
import state.agent.Agent;
import state.objective.Objective;

import java.io.Serializable;
import java.util.List;

import static engine.Game.DELTA_TIME;

public class Level implements ILevelDefinition, IRequiresGameEventMaster, Serializable {

    private LevelState levelState;
    private GameEventMaster eventMaster;

    public Level() {
        this.levelState = new LevelState();
        this.eventMaster.addRemoveAgentListener(removeAgentEvent -> levelState.removeAgent(removeAgentEvent.getAgent()));
    }

    public void injectGameEventMaster(GameEventMaster eventMaster) {
        this.eventMaster = eventMaster;
    }

    @Override
    public List<? extends IAgentDefinition> getDefinedAgents() {
        return levelState.getDefinedAgents();
    }

    @Override
    public void removeDefinedAgent(int index) {
        levelState.removeDefinedAgent(index);
    }

    @Override
    public void addIAgentDefinition(IAgentDefinition agent) {
        levelState.addDefinedAgent((Agent)agent);
    }

    @Override
    public List<? extends IAgentDefinition> getCurrentAgents() {
        return levelState.getCurrentAgents();
    }

    @Override
    public void removeAgent(int index) {
        levelState.removeCurrentAgent(index);
    }

    @Override
    public void addAgent(IAgentDefinition agent) {
        levelState.addCurrentAgent((Agent)agent);
    }

    public void step(double deltaTime) {

        for (Agent agent: levelState.getCurrentAgents()) {
            try {
                agent.update(levelState.getMutableAgentsExcludingSelf(agent), deltaTime);
            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            }
        }

        for (Objective objective: levelState.getObjectives())
            objective.execute(levelState);

        for (Agent agent: levelState.getCurrentAgents()) {

            //        TODO: update speed according to speed property
//            double newX = agent.getX() + (agent.getXVelocity() * DELTA_TIME);
//            double newY = agent.getY() + (agent.getYVelocity() * DELTA_TIME);
//            agent.setLocation(newX, newY);
        }

    }

}
