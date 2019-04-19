package panes;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import util.AuthoringContext;

import java.util.ResourceBundle;

/**
 * A pane that shows a console for notifying the user of changes and will eventually allow user input.
 *
 * @author Mary Stuart Elder
 * @author Samuel Rabinowitz
 *
 * Styling resources:
 *  https://www.w3.org/TR/css-backgrounds-3/#propdef-border-style
 *  https://stackoverflow.com/questions/48048943/javafx-8-scroll-bar-css
 */
public class ConsolePane extends AuthoringPane {

    public static final String OVERALL_STYLE = "terminal_overall";
    public static final String TITLE_STYLE = "terminal_heading";
    public static final String MESSAGE = "message";
    public static final String ERROR_MESSAGE = "error_message";
    public static final String SUCCESS_MESSAGE = "success_message";
    public static final String TITLE_TEXT = "Environment Console";
    public static final String STYLE = "console-pane.css";
    public static final double WIDTH = AuthoringEnvironment.DEFAULT_WIDTH;
    public static final double HEIGHT = AuthoringEnvironment.CONSOLE_HEIGHT;

    /**
     * Console message level.
     * @author Samuel Rabinowitz
     */
    public enum Level {
        NEUTRAL,
        ERROR,
        SUCCESS
    }

    private VBox myVBox;
    private VBox fullBox;
    private ScrollPane scrollPane;

    public ConsolePane(AuthoringContext context) {
        super(context);
        getContentChildren().add(initializeContent());
    }

    private Region initializeContent(){
        fullBox = new VBox();
        fullBox.getStylesheets().add(STYLE);
        fullBox.setId(OVERALL_STYLE);
        fullBox.setPrefSize(WIDTH, HEIGHT);
        myVBox = new VBox();
        scrollPane = new ScrollPane();
        formatScrollPane(scrollPane);
        scrollPane.setContent(myVBox);
        myVBox.setFillWidth(true);

        var title = new Text(TITLE_TEXT);
        title.setId(TITLE_STYLE);
        fullBox.getChildren().addAll(title, scrollPane);
        return fullBox;
    }

    private void formatScrollPane(ScrollPane consolePane){
        //scrollPane.setPrefViewportWidth(WIDTH);
        //scrollPane.setPrefViewportHeight(TITLE_HEIGHT + HEIGHT);
        consolePane.setId(MESSAGE);
        consolePane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        consolePane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    @Override
    public void setStylesheet(String url) {

    }

    @Override
    public void updateSize(double width, double height) {
        fullBox.setPrefSize(width, height);
    }

    /**
     * This method can be called externally to notify the user of environment changes.
     * Changes (good, neutral, and bad) to the environment that don't require immediate user action are sent to the console.
     * @param message String, message to send to user
     * @param level ConsolePane.Level, indicates the importance of the message
     */
    public void displayMessage(String message, Level level) {
        var text = new Text(message);

        if (level == Level.NEUTRAL) {
            text.setId(MESSAGE);
        }
        else if (level == Level.ERROR) {
            text.setId(ERROR_MESSAGE);
        }
        else if (level == Level.SUCCESS) {
            text.setId(SUCCESS_MESSAGE);
        }

        myVBox.getChildren().add(text);
        scrollPane.setVvalue(1);
    }

    public ScrollPane getConsole() {
        return scrollPane;
    }

}
