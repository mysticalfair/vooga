package engine;

import engine.event.GameEventMaster;
import gameengine.IAgentDefinition;
import gameengine.ILevelDefinition;
import state.IRequiresGameEventMaster;
import state.State;
import state.agent.Agent;
import state.agent.IAgent;
import state.objective.IObjective;
import state.objective.Objective;

import java.util.List;

import static engine.Game.DELTA_TIME;

public class Level implements ILevelDefinition, IRequiresGameEventMaster {

    private State state;
    private GameEventMaster eventMaster;

    public Level() {
        this.state = new State();
        this.eventMaster.addRemoveAgentListener(removeAgentEvent -> state.removeAgent(removeAgentEvent.getAgent()));
    }

    public void injectGameEventMaster(GameEventMaster eventMaster) {
        this.eventMaster = eventMaster;
    }

    @Override
    public List<? extends IAgentDefinition> getDefinedAgents() {
        return state.getDefinedAgents();
    }

    @Override
    public void removeDefinedAgent(int index) {
        state.removeDefinedAgent(index);
    }

    @Override
    public void addAgentDefinition(IAgentDefinition agent) {
        state.addDefinedAgent((Agent)agent);
    }

    @Override
    public List<? extends IAgentDefinition> getCurrentAgents() {
        return state.getCurrentAgents();
    }

    @Override
    public void removeAgent(int index) {
        state.removeCurrentAgent(index);
    }

    @Override
    public void addAgent(IAgentDefinition agent) {
        state.addCurrentAgent((Agent)agent);
    }

    public void step() {

        for (Agent agent: state.getCurrentAgents()) {
            try {
                agent.update(state.getMutableAgentsExcludingSelf(agent));
            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            }
        }

        for (Objective objective: state.getObjectives())
            objective.execute(state);

        for (IAgent agent: state.getCurrentAgents()) {

            double newX = agent.getX() + (agent.getXVelocity() * DELTA_TIME);
            double newY = agent.getY() + (agent.getYVelocity() * DELTA_TIME);
            agent.setLocation(newX, newY);
        }

    }

}
