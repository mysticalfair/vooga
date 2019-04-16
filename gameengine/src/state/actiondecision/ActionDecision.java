package state.actiondecision;

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
public class ActionDecision implements Serializable{

    private Action action;
    private List<Condition> conditions;

    public ActionDecision(IActionDefinition action, List<IConditionDefinition> conditions) {
        this.action = (Action)action;
        // this.conditions = conditions.stream().map(c -> c);
    }

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

}
