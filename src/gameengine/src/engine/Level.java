package engine;

import authoring.exception.PropertyDoesNotExistException;
import engine.event.GameEventMaster;
import authoring.IAgentDefinition;
import authoring.ILevelDefinition;
import engine.event.events.AddAgentEvent;
import engine.event.events.RemoveAgentEvent;
import state.AgentReference;
import state.IPlayerLevelState;
import state.IRequiresGameEventMaster;
import state.LevelState;
import state.Property;
import state.agent.Agent;
import state.objective.Objective;

import java.awt.geom.Point2D;
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

    private List<List<Point2D>> paths;

    public Level(List<Agent> masterDefinedAgents) {
        this.levelState = new LevelState();
        this.agentsToAdd = new ArrayList<>();
        this.agentsToRemove = new ArrayList<>();
        this.authoringAgentsPlaced = new ArrayList<>();
        this.authoringPlaceableAgents = new ArrayList<>();
        this.masterDefinedAgents = masterDefinedAgents;
        this.paths = new ArrayList<>();
    }

    public void injectGameEventMaster(GameEventMaster eventMaster) {
        this.eventMaster = eventMaster;
        this.eventMaster.addRemoveAgentListener((Consumer<RemoveAgentEvent> & Serializable) (removeAgentEvent) ->
                    setAgentToRemove(removeAgentEvent.getAgent()));
        this.eventMaster.addAddAgentListener((Consumer<AddAgentEvent> & Serializable) addAgentEvent ->
                    setAgentToAdd(createAgentFromReference(addAgentEvent.getAgentReference())));
    }

    @Override
    public List<AgentReference> getCurrentAgents() {
        return authoringAgentsPlaced;
    }

    private List<Agent> createAgentsFromReferences() {
        List<Agent> agents = new ArrayList<>();
        for (AgentReference agentReference: authoringAgentsPlaced) {
            Agent a = createAgentFromReference(agentReference);
            if (a != null)
                agents.add(a);
        }
        return agents;
    }

    private Agent createAgentFromReference(AgentReference agentReference) {
        for (Agent a : masterDefinedAgents) {
            if (a.getName().equals(agentReference.getName())) {
                try {
                    Agent clone = a.clone();
                    clone.setLocation(agentReference.getX(), agentReference.getY());
                    clone.setDirection(agentReference.getDirection());
                    clone.addProperties(agentReference.getInstanceProperties());
                    return clone;
                } catch (CloneNotSupportedException e) {
                    // Do nothing, that agent does not support cloning
                }
            }
        }
        // Agent does not exist, return null. TODO: Use exception handling
        return null;
    }

    @Override
    public void removeAgent(int index) {
        authoringAgentsPlaced.remove(index);
    }

    @Override
    public void addAgent(String agentName, int x, int y, double direction, List<Property> instanceProperties) {
        authoringAgentsPlaced.add(new AgentReference(agentName, x, y, direction, instanceProperties));
    }

    @Override
    public List<String> getPlaceableAgents() {
        return authoringPlaceableAgents;
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

    @Override
    public List<List<Point2D>> getPaths() {
        return paths;
    }

    @Override
    public void removePath(int index) {
        paths.remove(index);
    }

    @Override
    public void addPath(List<Point2D> path) {
        paths.add(path);
    }

    public void step(double deltaTime) {
        int index = 0;
        for (Agent agent: levelState.getCurrentAgents()) {
            try {
                agent.update(levelState.getMutableAgentsExcludingSelf(agent), deltaTime);
                index++;

            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            } catch (PropertyDoesNotExistException e) {
                System.out.println(e.getMessage());
            }
        }

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
        for (Agent agent : createAgentsFromReferences()) {
            levelState.addCurrentAgent(agent);
        }
    }
}
