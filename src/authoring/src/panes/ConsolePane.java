package panes;

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
    private ScrollPane consolePane;

    public static final String OVERALL_STYLE = "terminal_overall";
    public static final String TITLE_STYLE = "terminal_heading";
    public static final String TEXT_STYLE = "terminal";
    public static final String TITLE_TEXT = "Environment Console";
    public static final double HEIGHT = AuthoringEnvironment.DEFAULT_HEIGHT/8;
    public static final double TITLE_HEIGHT = 60;

    public ConsolePane() {
        super();
        getContentChildren().add(initializeContent());
    }

    private Region initializeContent(){
        var fullBox = new VBox();
        fullBox.setId(OVERALL_STYLE);
        myVBox = new VBox();
        consolePane = new ScrollPane();
        formatScrollPane(consolePane);
        consolePane.setContent(myVBox);
        myVBox.setFillWidth(true);

        var title = new Text(TITLE_TEXT);
        title.setId(TITLE_STYLE);
        fullBox.getChildren().addAll(title, consolePane);
        return fullBox;
    }

    private void formatScrollPane(ScrollPane consolePane){
        consolePane.setPrefViewportWidth(AuthoringEnvironment.DEFAULT_WIDTH);
        consolePane.setPrefViewportHeight(TITLE_HEIGHT + HEIGHT);
        consolePane.setId(TEXT_STYLE);
    }

    @Override
    public void setStylesheet(String url) {

    }

    /**
     * This method can be called externally to notify the user of environment changes.
     * Changes (good, neutral, and bad) to the environment that don't require immediate user action are sent to the console.
     * @param message String, message to send to user
     */
    public void displayConsoleMessage(String message){
        var text = new Text(message);
        text.setId(TEXT_STYLE);
        myVBox.getChildren().add(text);
    }

    public ScrollPane getConsole() {
        return consolePane;
    }

}
