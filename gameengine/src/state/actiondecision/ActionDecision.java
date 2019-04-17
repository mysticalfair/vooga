package state.actiondecision;

import authoring.IActionDecisionDefinition;
import authoring.IActionDefinition;
import authoring.IConditionDefinition;
import state.action.Action;
import state.agent.Agent;
import state.condition.Condition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to decide when to do an action, and execute that action if necessary
 * @author David Miron
 * @author Jorge Raad
 */
public class ActionDecision implements IActionDecisionDefinition, Serializable{

    private Action action;
    private List<Condition> conditions;

    public ActionDecision(Action action, List<Condition> conditions) {
        this.action = action;
        this.conditions = conditions;
    }

    /**
     * Execute the action on agents passed, after filtering based on conditions
     * @param agents The list of active agents
     */
    public void execute(List<Agent> agents, double deltaTime) throws CloneNotSupportedException {
        List<Agent> agentsFiltered = new ArrayList<>(agents);

        for (Condition condition: conditions)
            agentsFiltered = condition.getValid(agentsFiltered);

        for (Agent agent: agentsFiltered)
            action.execute(agent, deltaTime);
    }

    @Override
    public IActionDefinition getAction() {
        return action;
    }

    @Override
    public void setAction(IActionDefinition actionDefinition) {
        this.action = (Action)actionDefinition;
    }

    @Override
    public List<? extends IConditionDefinition> getConditions() {
        return conditions;
    }

    @Override
    public void removeCondition(int index) {
        conditions.remove(index);
    }

    @Override
    public void addCondition(IConditionDefinition conditionDefinition) {
        conditions.add((Condition)conditionDefinition);
    }
}
