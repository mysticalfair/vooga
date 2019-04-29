
import engine.Game;
import engine.IPlayerGame;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameApplication extends Application {
   private static final int MILLISECOND_DELAY = 100;

   public static void main(String[] args) {
      launch(args);
   }

   private Stage primaryStage;
   private Scene myScene;
   private Group root;
   public static final int SCREEN_WIDTH = 800;
   public static final int SCREEN_HEIGHT = 800;
   public static final Paint BACKGROUND = Color.WHITE;
   public static final String GAME_NAME = "Game Time";
   public static final String GAME_FILE_NAME = "ClashTest1.xml";
   private Timeline animation;
   private IPlayerGame game;

   @Override
   public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      primaryStage.show();
      setAnimation();
      game = new Game();
      game.loadState("src/gameengine/resources/gamefiles/" + GAME_FILE_NAME);
      var levelState = this.game.getLevelState();
      Level level = new Level(levelState);
      this.primaryStage.setScene(level);
      this.primaryStage.show();
   }

   private void step(){
      game.step();
   }

   private void setAnimation() {
         animation = new Timeline();
         var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), event -> step());
         animation.setCycleCount(Timeline.INDEFINITE);
         animation.getKeyFrames().add(frame);
         animation.play();
   }
}
