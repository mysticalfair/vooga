package engine;

import engine.event.GameEventMaster;
import authoring.IAgentDefinition;
import authoring.ILevelDefinition;
import engine.event.events.AddAgentEvent;
import engine.event.events.RemoveAgentEvent;
import state.IRequiresGameEventMaster;
import state.LevelState;
import state.agent.Agent;
import state.objective.attributeObjective;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Level implements ILevelDefinition, IRequiresGameEventMaster, Serializable {

    private LevelState levelState;
    private GameEventMaster eventMaster;

    private List<Agent> agentsToAdd;
    private List<Agent> agentsToRemove;

    public Level() {
        this.levelState = new LevelState();
        this.agentsToAdd = new ArrayList<>();
        this.agentsToRemove = new ArrayList<>();
    }

    public void injectGameEventMaster(GameEventMaster eventMaster) {
        this.eventMaster = eventMaster;
        this.eventMaster.addRemoveAgentListener((Consumer<RemoveAgentEvent> & Serializable) (removeAgentEvent) ->
                    setAgentToRemove(removeAgentEvent.getAgent()));
        this.eventMaster.addAddAgentListener((Consumer<AddAgentEvent> & Serializable) addAgentEvent ->
                    setAgentToAdd(addAgentEvent.getAgent()));
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
                System.out.print("Position: " + (int)agent.getX() + ", " + (int)agent.getY() + "| ");
                System.out.print("Angle: " + (int)agent.getDirection() + "| ");
            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            }
        }
        System.out.println("______________________________________________________");

        for (attributeObjective objective: levelState.getObjectives())
            objective.execute(levelState);

        updateAgentsList();
    }

    private void updateAgentsList() {
        for (Agent agent: agentsToAdd)
            levelState.addCurrentAgent(agent);

        for (Agent agent: agentsToRemove)
            levelState.removeAgent(agent);

        agentsToAdd.clear();
        agentsToRemove.clear();
    }

    private void setAgentToAdd(Agent agent) {
        agentsToAdd.add(agent);
    }

    private void setAgentToRemove(Agent agent) {
        agentsToRemove.add(agent);
    }

}
