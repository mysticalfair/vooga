import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AuthoringEnvironment extends Application {

    static final String TITLE = "Vooginas!";
    static final int DEFAULT_WIDTH = 800;
    static final int DEFAULT_HEIGHT = 500;

    private BorderPane borderPane;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        borderPane = new BorderPane();
        Scene mainScene = new Scene(borderPane, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        stage.setTitle(TITLE);
        stage.setScene(mainScene);
        //stage.getScene().getStylesheets().add("Blue.css");
        stage.show();
    }

}