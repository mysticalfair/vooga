package state;

import state.action.IAction;
import state.action.collision.MeleeOnCollision;
import state.action.collision.ShooterOnCollision;
import state.actiondecision.ActionDecision;
import state.agent.Agent;
import state.agent.IAgent;
import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;
import state.attribute.IAttribute;
import state.condition.CollisionCondition;
import state.condition.Condition;
import state.condition.RangeCondition;
import state.objective.IPlayerObjective;
import state.objective.IObjective;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Luke_Truitt
 * Version of state that is passed to player
 */
public class State implements IState {

    private List<IAgent> agentsOptions;
    private List<IAgent> agentsCurrent;
    private List<IObjective> objectivesCurrent;
    private List<IAttribute> attributesCurrent;

    public State() {
        this.agentsOptions = new ArrayList<>();
        this.agentsCurrent = new ArrayList<>();
        this.objectivesCurrent = new ArrayList<>();
        this.attributesCurrent = new ArrayList<>();
    }

    /**
     * For Engine
     */
    public List<IAgent> getMutableOptions() {
        return this.agentsOptions;
    }

    public void setOptionAgents(List<IAgent> agentOptions) {
        this.agentsOptions = agentOptions;
    }

    public List<IAgent> getMutableAgents() {
        return this.agentsCurrent;
    }

    public void setCurrentAgents(List<IAgent> agentCurrent) {
        this.agentsCurrent = agentCurrent;
    }

    public List<IObjective> getMutableObjectives() {
        return this.objectivesCurrent;
    }

    public void setObjectives(List<IObjective> objectivesCurrent) {
        this.objectivesCurrent = objectivesCurrent;
    }

    public List<IAttribute> getMutableAttributes() { return this.attributesCurrent; }

    public void setAttributes(List<IAttribute> attributesCurrent) {
        this.attributesCurrent = attributesCurrent;
    }

    @Override
    public List<IAgent> getMutableAgentsExcludingSelf(IAgent agent) {
        List<IAgent> agentsWithoutSelf = new ArrayList<>(agentsCurrent);
        agentsWithoutSelf.removeIf(a -> a == agent);
        return agentsWithoutSelf;
    }

    /**
     * For Author
     */
    public void defineAgent(IAgent agent) {
        this.agentsOptions.add(agent);
    }
    public void placeAgent(IAgent agent) {
        this.agentsCurrent.add(agent);
    }
    public void defineObjective(IObjective objective) {
        this.objectivesCurrent.add(objective);
    }
    public void defineAttribute(IAttribute attribute) {
        this.attributesCurrent.add(attribute);
    }

    /**
     * For Player - Iterate through and update your copy based on the corresponding ID.
     */
    public Iterable<IPlayerAgent> getImmutableOptions() { return List.copyOf(this.agentsOptions); }

    public Iterable<IPlayerAgent> getImmutableAgents() {
        return List.copyOf(this.agentsCurrent);
    }

    public Iterable<IPlayerObjective> getImmutableObjectives() {
        return List.copyOf(this.objectivesCurrent);
    }

    public Iterable<IPlayerAttribute> getImmutableAttributes() {
        return List.copyOf(this.attributesCurrent);
    }

    public void removeAgent(IAgent agent) {
        if(this.agentsCurrent.contains(agent)){
            this.agentsCurrent.remove(agent);
        }
    }

    public IAgent[] getAgents() {
        Agent towerAgent = new Agent(0, 100, 100, "Jorge", "Bad Guys",50, 10, 10, 0, 90, 10);

        IAction action2 = new ShooterOnCollision(towerAgent);
        List<Condition> conditions2 = new ArrayList<>();

        conditions2.add(new RangeCondition(towerAgent, 20));


        ActionDecision actionDecision2 = new ActionDecision(action2, conditions2);

        towerAgent.addActionDecisionRaw(actionDecision2);

        return new Agent[]{towerAgent};
    }



}
