package frontend_objects;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TestWindow extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Test stage");
        stage.setScene(dragStage());
        stage.getScene().getStylesheets().add("Blue.css");
        stage.show();
    }

    private Scene dragStage(){
        StackPane bigPane = new StackPane();
        BorderPane structure = new BorderPane();
        Pane pane = new Pane();
        bigPane.getChildren().addAll(structure, pane);
        structure.setCenter(new Text("Hi there"));
        AgentViewTest agent = new AgentViewTest("Tower.jpg");
        pane.getChildren().add(agent.getView());
        return new Scene(bigPane, 400, 400);
    }


}
