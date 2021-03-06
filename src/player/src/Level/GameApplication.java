package Level;

import Level.Level;
import SplashScreen.SplashScene;
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
   private static final int MILLISECOND_DELAY = 10;

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
   private Timeline animation;
   private IPlayerGame game;
   private SplashScene ss;

   @Override
   public void start(Stage primaryStage) {
      this.primaryStage = primaryStage;
      ss = new SplashScene(primaryStage, this);
      primaryStage.setScene(ss.pickGame());
      this.primaryStage.show();
     /* game = ss.selectGame();
      var levelState = game.getLevelState();
      Level level = new Level(levelState);
      this.primaryStage.setScene(level);
      this.primaryStage.show();*/
      //setAnimation();

   }

   public void setup()
   {
      System.out.println("in setup");
      game = ss.selectGame();
      var levelState = game.getLevelState();
      Level level = new Level(levelState);
      this.primaryStage.setScene(level);
      this.primaryStage.show();
      setAnimation();
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
