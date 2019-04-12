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
    private VBox fullBox;
    private ScrollPane consolePane;

    public static final String OVERALL_STYLE = "terminal_overall";
    public static final String TITLE_STYLE = "terminal_heading";
    public static final String TEXT_STYLE = "terminal";
    public static final String TITLE_TEXT = "Environment Console";
    public static final String STYLE = "console-pane.css";
    public static final double WIDTH = AuthoringEnvironment.DEFAULT_WIDTH;
    public static final double HEIGHT = AuthoringEnvironment.CONSOLE_HEIGHT;

    public ConsolePane() {
        super();
        getContentChildren().add(initializeContent());
    }

    private Region initializeContent(){
        fullBox = new VBox();
        fullBox.getStylesheets().add(STYLE);
        fullBox.setId(OVERALL_STYLE);
        fullBox.setPrefSize(WIDTH, HEIGHT);
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
        //consolePane.setPrefViewportWidth(WIDTH);
        //consolePane.setPrefViewportHeight(TITLE_HEIGHT + HEIGHT);
        consolePane.setId(TEXT_STYLE);
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
