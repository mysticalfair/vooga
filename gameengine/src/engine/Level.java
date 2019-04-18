package engine;

import engine.event.GameEventMaster;
import authoring.IAgentDefinition;
import authoring.ILevelDefinition;
import engine.event.events.AddAgentEvent;
import engine.event.events.RemoveAgentEvent;
import state.IRequiresGameEventMaster;
import state.LevelState;
import state.agent.Agent;
import state.objective.Objective;

import java.io.Serializable;
import java.util.List;
import java.util.function.Consumer;

public class Level implements ILevelDefinition, IRequiresGameEventMaster, Serializable {

    private LevelState levelState;
    private GameEventMaster eventMaster;

    public Level() {
        this.levelState = new LevelState();
    }

    public void injectGameEventMaster(GameEventMaster eventMaster) {
        this.eventMaster = eventMaster;
        this.eventMaster.addRemoveAgentListener((Consumer<RemoveAgentEvent> & Serializable) (removeAgentEvent) -> levelState.removeAgent(removeAgentEvent.getAgent()));
        this.eventMaster.addAddAgentListener((Consumer<AddAgentEvent> & Serializable) addAgentEvent -> levelState.addCurrentAgent(addAgentEvent.getAgent()));
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
                System.out.print(agent.getX() + ", " + agent.getY() + "| ");
            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            }
        }
        System.out.println();

        for (Objective objective: levelState.getObjectives())
            objective.execute(levelState);
    }

}
