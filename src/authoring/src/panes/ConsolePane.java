package panes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConsolePane extends AuthoringPane{

    private VBox myVBox;

    public ConsolePane() {
        super();
        getContentChildren().add(initializeContent());
    }

    private Region initializeContent(){
        ScrollPane consolePane = new ScrollPane();
        myVBox = new VBox();
        myVBox.getChildren().add(new Text("Testing text in console pane"));
        consolePane.setContent(myVBox);
        return consolePane;
    }

    @Override
    public void setStylesheet(String url) {

    }

    public void addButton(String label, EventHandler action) {
        var button = new Button(label);
        button.setOnAction(action);
        myVBox.getChildren().add(button);
    }

    /**
     * This method can be called externally to notify the user of environment changes.
     * Changes (good, neutral, and bad) to the environment that don't require immediate user action are sent to the console.
     * @param message String, message to send to user
     */
    public void displayConsoleMessage(String message){
        myVBox.getChildren().add(new Text(message));
    }
}
