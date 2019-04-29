package panes.attributes;

import authoring.IAgentDefinition;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import panes.AuthoringPane;
import panes.ConsolePane;
import panes.attributes.agent.define.DefineAgentForm;
import util.AuthoringContext;

import java.util.function.Consumer;

public class AttributesPane extends AuthoringPane {

    private ScrollPane scrollPane;

    public AttributesPane(AuthoringContext context) {
        super(context);
        setStylesheet(getContext().getString("AttributesStyle"));
        
        scrollPane = new ScrollPane();
        updateSize(getContext().getDouble("AttributesWidth"), getContext().getDouble("MiddleRowHeight"));
        //scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        getContentChildren().add(scrollPane);
    }

    @Override
    public void updateSize(double width, double height) {
        scrollPane.setPrefViewportWidth(width - getContext().getDouble("AttributesPadding"));
        scrollPane.setPrefViewportHeight(height - getContext().getDouble("AttributesPadding"));
    }

    public double getWidth() {
        return scrollPane.getWidth();
    }

    /**
     * Creates the define new agent form in the attributes pane. Has the option
     * to modify an existing agent, or to create a new agent based off an
     * existing one. If creating a new agent, the form will save to the context
     * state's defined agent list. If modifying an existing agent, the form
     * will update all of the attributes of the existing agent passed in. If
     * an agent is unsuccessfully created, will notify user of problem to fix
     * and will not clear form.
     * @param onCreated callback for successful agent creation
     * @param existingAgent an existing agent to modify or copy, if null
     *                      creates new agent
     * @param copyExisting if true and existingAgent not null, creates a new
     *                     agent based off of existingAgent
     */
    public void createNewAgentForm(Consumer<IAgentDefinition> onCreated, IAgentDefinition existingAgent, boolean copyExisting) {
        scrollPane.setContent(null);
        DefineAgentForm defineAgentForm = new DefineAgentForm(getContext());
        if (existingAgent != null) {
            defineAgentForm.loadFromExisting(existingAgent);
        }
        defineAgentForm.accessContainer(scrollPane::setContent);
        defineAgentForm.setOnCancel(e -> scrollPane.setContent(null));
        defineAgentForm.setOnSave(e -> {
            IAgentDefinition a = defineAgentForm.getAgentDefinition();
            if (a == null) {
                getContext().displayConsoleMessage(getContext().getString("AgentNotCreated"), ConsolePane.Level.ERROR);
                return;
            }
            String testString = String.format("New agent created: %s\nx: %d y: %d w: %d h: %d\nimage: %s\n" +
                    "number of properties: %d\nnumber of action decisions: %d",
                    a.getName(),
                    (int) a.getX(), (int) a.getY(), a.getWidth(), a.getHeight(),
                    a.getImageURL(),
                    a.getProperties().size(),
                    a.getActionDecisions().size()
                    );
            getContext().displayConsoleMessage(testString, ConsolePane.Level.SUCCESS);
            if (existingAgent != null && !copyExisting) {
                existingAgent.setName(a.getName());
                existingAgent.setWidth(a.getWidth());
                existingAgent.setHeight(a.getHeight());
                existingAgent.setImageURL(a.getImageURL());
                existingAgent.getProperties().clear();
                a.getProperties().forEach(existingAgent::addProperty);
                existingAgent.getActionDecisions().clear();
                a.getActionDecisions().forEach(existingAgent::addActionDecision);
                onCreated.accept(existingAgent);
            }
            else {
                getContext().getState().addDefinedAgent(a);
                onCreated.accept(a);
            }
            scrollPane.setContent(null);
        });
    }

}
