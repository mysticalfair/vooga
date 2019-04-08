package frontend_objects;

public class AgentView extends DraggableView {

    /**
     * Extends frontend_objects.DraggableView for dragging the tower image into the map
     * @author Mary Stuart Elder
     */

    public static final String STYLE = "img";
    public static final int SIZE = 100;

    public AgentView(String imageName){
        super(true);
        formatView(imageName, STYLE, SIZE, SIZE);
    }

    public AgentView(AgentView otherAgent){
        super(otherAgent, true);
    }

    /*
    Need the DraggableView classes to have methods that allow the View to be dragged anywhere
    In the case of Agents:
        If you've released the mouse and an agent can be placed, a copy of the Agent should be stored in both the original location and the final location

    Attributes of an AgentView
        Characteristics of an Agent
            Size
            Speed?
            Health
            Whatever
        Image associated with it
        Location

     */
}
