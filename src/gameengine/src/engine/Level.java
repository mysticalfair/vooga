package engine;

import engine.event.GameEventMaster;
import authoring.IAgentDefinition;
import authoring.ILevelDefinition;
import engine.event.events.AddAgentEvent;
import engine.event.events.RemoveAgentEvent;
import state.AgentReference;
import state.IPlayerLevelState;
import state.IRequiresGameEventMaster;
import state.LevelState;
import state.agent.Agent;
import state.objective.Objective;

import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Level implements ILevelDefinition, IRequiresGameEventMaster, Serializable {

    private LevelState levelState;
    private GameEventMaster eventMaster;

    private List<Agent> agentsToAdd;
    private List<Agent> agentsToRemove;

    private List<AgentReference> authoringAgentsPlaced;
    private List<String> authoringPlaceableAgents;

    private List<Agent> masterDefinedAgents;

    public Level(List<Agent> masterDefinedAgents) {
        this.levelState = new LevelState();
        this.agentsToAdd = new ArrayList<>();
        this.agentsToRemove = new ArrayList<>();
        this.authoringAgentsPlaced = new ArrayList<>();
        this.authoringPlaceableAgents = new ArrayList<>();
        this.masterDefinedAgents = masterDefinedAgents;
    }

    public void injectGameEventMaster(GameEventMaster eventMaster) {
        this.eventMaster = eventMaster;
        this.eventMaster.addRemoveAgentListener((Consumer<RemoveAgentEvent> & Serializable) (removeAgentEvent) ->
                    setAgentToRemove(removeAgentEvent.getAgent()));
        this.eventMaster.addAddAgentListener((Consumer<AddAgentEvent> & Serializable) addAgentEvent ->
                    setAgentToAdd(addAgentEvent.getAgent()));
    }

    @Override
    public List<? extends IAgentDefinition> getCurrentAgents() {
        return createAgentsFromDefinitions();
    }

    private List<Agent> createAgentsFromDefinitions() {
        List<Agent> agents = new ArrayList<>();
        for (AgentReference agentReference: authoringAgentsPlaced) {
            for (Agent a: masterDefinedAgents) {
                if (a.getName().equals(agentReference.getName())) {
                    try {
                        Agent clone = a.clone();
                        clone.setLocation(agentReference.getX(), agentReference.getY());
                        clone.setDirection(agentReference.getDirection());
                        agents.add(clone);
                    } catch (CloneNotSupportedException e) {
                        // Do nothing, that agent does not support cloning
                    }
                    break;
                }
            }
        }
        return agents;
    }

    @Override
    public void removeAgent(int index) {
        authoringAgentsPlaced.remove(index);
    }

    @Override
    public void addAgent(String agentName, int x, int y, double direction) {
        authoringAgentsPlaced.add(new AgentReference(agentName, x, y, direction));
    }

    @Override
    public List<? extends IAgentDefinition> getPlaceableAgents() {
        return null;
    }

    @Override
    public void removePlaceableAgent(int index) {
        authoringPlaceableAgents.remove(index);
    }

    @Override
    public void removePlaceableAgent(String agentName) {
        authoringPlaceableAgents.remove(agentName);
    }

    @Override
    public void addPlaceableAgent(String agentName) {
        authoringPlaceableAgents.add(agentName);
    }

    public void step(double deltaTime) {

        for (Agent agent: levelState.getCurrentAgents()) {
            try {
                System.out.print("Position: " + (int)agent.getX() + ", " + (int)agent.getY() + "| ");

                agent.update(levelState.getMutableAgentsExcludingSelf(agent), deltaTime);
//                System.out.print("Position: " + (int)agent.getX() + ", " + (int)agent.getY() + "| ");
//                System.out.print("Angle: " + (int)agent.getDirection() + "| ");

            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            }
        }
//        System.out.println("______________________________________________________");

        for (Objective objective: levelState.getObjectives())
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

    private void setLevelState(LevelState state) {
        this.levelState = state;
    }

    public IPlayerLevelState getLevelState(){return this.levelState;}

    public void initializeAgents() {
        for (Agent agent : createAgentsFromDefinitions()) {
            levelState.addCurrentAgent(agent);
        }
    }
}
