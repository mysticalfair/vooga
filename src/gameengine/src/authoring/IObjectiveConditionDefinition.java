package authoring;

import java.util.Map;

/**
 * Interface for authoring environment to use to define an objective.
 * @author Jamie Palka
 */
public interface IObjectiveConditionDefinition {

    String getTitle();
    void setTitle(String title);

    int getLevel();
    void setLevel(int level);

    Map<String, Object> getParams();
    void setParams(Map<String, Object> params);

}
