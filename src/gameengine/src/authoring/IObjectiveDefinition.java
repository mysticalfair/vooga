package authoring;

import state.objective.IObjectiveOutcome;

import java.util.Map;

/**
 * Interface for authoring environment to use to define an objective.
 * @author Jamie Palka
 */
public interface IObjectiveDefinition {

    String getTitle();
    void setTitle(String title);

    int getId();
    void setId(int id);

    IObjectiveOutcome getOutcome();
    void setOutcome(IObjectiveOutcome outcome);

    int getLevel();
    void setLevel(int level);

    Map<String, Object> getParams();
    void setParams(Map<String, Object> params);

}
