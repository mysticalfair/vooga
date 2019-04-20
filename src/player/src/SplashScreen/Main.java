package player.src.SplashScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    private Scene myScene;
    private Stage myStage;

    public void start (Stage stage) {
        // attach scene to the stage and display it
        myStage = stage;
        SplashScene ss = new SplashScene(myStage);
        myScene = ss.pickGame();
        myStage.setScene(myScene);

        myStage.show();
        // attach "game loop" to timeline to play it

    }

}
