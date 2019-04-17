package authoring;

import java.util.List;

/**
 * Interface for authoring environment to use to define an action
 * @author David Miron
 */
public interface IActionDecisionDefinition {

    IActionDefinition getAction();
    void setAction(IActionDefinition actionDefinition);

    List<? extends IConditionDefinition> getConditions();
    void removeCondition(int index);
    void addCondition(IConditionDefinition conditionDefinition);


}
