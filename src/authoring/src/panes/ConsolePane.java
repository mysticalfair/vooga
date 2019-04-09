package panes;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Styling resources:
 *  https://www.w3.org/TR/css-backgrounds-3/#propdef-border-style
 *  https://stackoverflow.com/questions/48048943/javafx-8-scroll-bar-css
 */

public class ConsolePane extends AuthoringPane{

    private VBox myVBox;

    public ConsolePane() {
        super();
        getContentChildren().add(initializeContent());
    }

    private Region initializeContent(){
        var fullBox = new VBox();
        fullBox.setId("terminal_overall");
        myVBox = new VBox();
        ScrollPane consolePane = new ScrollPane();
        formatScrollPane(consolePane);
        //myVBox.getChildren().add(new Text("Testing text in console pane"));
        consolePane.setContent(myVBox);
        myVBox.setFillWidth(true);

        var title = new Text("Environment Console");
        title.setId("terminal_heading");
        fullBox.getChildren().addAll(title, consolePane);
        return fullBox;
    }

    private void formatScrollPane(ScrollPane consolePane){
        consolePane.setPrefViewportWidth(1000);
        consolePane.setPrefViewportHeight(100);
        consolePane.setId("terminal");
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
        var text = new Text(message);
        text.setId("terminal");
        myVBox.getChildren().add(text);
    }
}
