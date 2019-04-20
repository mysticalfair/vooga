
import javafx.application.Application;
import javafx.stage.Stage;

public class GameApplication extends Application {
   public static void main(String[] args) {
      launch(args);
   }

   public void start(Stage stage) {
      Level level = new Level();
      stage.setScene(level);
      stage.show();
   }
}
