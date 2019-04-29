package authoring;

import java.util.Map;

public interface IObjectiveOutcomeDefinition extends INameFieldsDefinition {

    String getTitle();
    void setTitle(String title);

    Map<String, Object> getParams();
    void setParams(Map<String, Object> params);

}
