package authoring;

import java.util.Map;

/**
 * Interface that contains methods to define some object with a name and list of fields (parameters)
 * @author David Miron
 */
public interface INameFieldsDefinition {

    String getName();
    void setName(String name);

    Map<String, String> getParams();
    void setParams(Map<String, String> params);
    // TODO: should be a map from String to Field, and field can have things like AgentOperation, Agent, etc.
}
