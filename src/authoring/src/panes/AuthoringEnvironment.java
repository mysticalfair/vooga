package panes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AuthoringEnvironment extends Application {

    static final String TITLE = "Vooginas!";
    static final double DEFAULT_WIDTH = 1000;
    static final double DEFAULT_HEIGHT = 600;

    private StackPane stackPane;
    private BorderPane borderPane;
    private ConsolePane consolePane;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stackPane = new StackPane();
        borderPane = new BorderPane();
        stackPane.getChildren().add(borderPane);
        initAllPanes();
        initStage(stage);
    }

    private void initAllPanes() {
        initConsolePane();
        initAgentPane();
        initAttributesPane();
    }

    private void initAgentPane() {
        AgentPane agentPane = new AgentPane(this);
        agentPane.addButton("hi back!", e -> consolePane.displayConsoleMessage("Button was pressed"));
        agentPane.accessContainer(node -> borderPane.setRight(node));
    }

    private void initAttributesPane() {
        AttributesPane attributesPane = new AttributesPane(this);
        attributesPane.accessContainer(node -> borderPane.setLeft(node));
    }

    private void initConsolePane() {
        consolePane = new ConsolePane(this);
        consolePane.accessContainer(node -> borderPane.setBottom(node));
    }

    private void initStage(Stage stage) {
        Scene mainScene = new Scene(stackPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(mainScene);
        stage.setMinWidth(DEFAULT_WIDTH);
        stage.setMinHeight(DEFAULT_HEIGHT);
        //stage.getScene().getStylesheets().add("Blue.css");
        stage.show();
    }

    private void printMessage(String message){
        System.out.println(message);
    }

}