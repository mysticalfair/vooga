package gameengine;

import gameengine.IActionDecisionDefinition;

import java.util.List;

/**
 * Interface for the authoring environment to use to define an agent
 * @author David Miron
 */
public interface IAgentDefinition {

    int getWidth();
    void setWidth(int width);

    int getHeight();
    void setHeight(int height);

    String getImageURL();
    void setImageURL(String imageURL);

    String getName();
    void setName(String name);

    List<IActionDecisionDefinition> getActionDecisions();
    void removeActionDecision(int index);
    void addActionDecision(IActionDecisionDefinition actionDecision);
}
