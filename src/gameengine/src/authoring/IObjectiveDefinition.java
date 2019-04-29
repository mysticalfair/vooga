package authoring;

import state.objective.ObjectiveCondition;
import state.objective.ObjectiveOutcome;

public interface IObjectiveDefinition {

    IObjectiveOutcomeDefinition getOutcome();
    void setOutcome(IObjectiveOutcomeDefinition outcome);

    IObjectiveConditionDefinition getCondition();
    void setCondition(IObjectiveConditionDefinition condition);

}
