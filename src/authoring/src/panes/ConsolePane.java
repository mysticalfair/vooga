package panes;

import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConsolePane extends AuthoringPane{

    private VBox myVBox;

    public ConsolePane(AuthoringEnvironment author) {
        super(author);
        getContentChildren().add(initializeContent());
    }

    private Region initializeContent(){
        ScrollPane consolePane = new ScrollPane();
        consolePane.setPrefViewportWidth(400.0);
        consolePane.setPrefViewportHeight(50.0);
        myVBox = new VBox();
        myVBox.getChildren().add(new Text("Testing text in console pane"));
        var box = new HBox();
        myVBox.getChildren().add(box);
        consolePane.setContent(myVBox);
        return consolePane;
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void addButton(String label, EventHandler action) {

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
