package panes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AuthoringEnvironment extends Application {

    static final String TITLE = "Vooginas!";
    static final int DEFAULT_WIDTH = 800;
    static final int DEFAULT_HEIGHT = 600;

    private StackPane stackPane;
    private BorderPane borderPane;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stackPane = new StackPane();
        borderPane = new BorderPane();
        stackPane.getChildren().add(borderPane);
        Scene mainScene = new Scene(stackPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        AgentPane agentPane = new AgentPane();
        agentPane.accessNode(node -> borderPane.setRight(node));

        stage.setTitle(TITLE);
        stage.setScene(mainScene);
        stage.setMinWidth(DEFAULT_WIDTH);
        stage.setMinHeight(DEFAULT_HEIGHT);
        //stage.getScene().getStylesheets().add("Blue.css");
        stage.show();
    }

}