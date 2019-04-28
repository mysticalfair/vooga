package authoring;

import state.objective.IObjectiveOutcome;

/**
 * Interface for authoring environment to use to define an objective.
 * @author Jamie Palka
 */
public interface IObjectiveDefinition<T> {

    void setOutcome(IObjectiveOutcome outcome);

    void setExistenceTargetValue(int targetValue);
    void setAgentPropertyTargetValue(T targetValue);

}
