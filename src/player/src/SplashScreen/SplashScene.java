package SplashScreen;
import engine.Game;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import java.io.File;
import Level.Level;


public class SplashScene {

    private static final String GAMEDIR = "gamefiles/";
    private Stage myStage;
    private String myGame;
    private Pane root;
    private Scene scene;

    public SplashScene(Stage stage)
    {
        myStage = stage;
        root = new Pane();
        scene = new Scene(root, 1000,600);
        scene.getStylesheets().add(this.getClass().getClassLoader().getResource("splashstyle.css").toExternalForm());
        root.setId("pane");

    }

    public Scene pickGame()
    {
        root = new BorderPane();
        HBox box = new HBox();
        box.setSpacing(200);
        box.setLayoutX(380);
        box.setLayoutY(300);

        box.setPrefSize(100,100);
        SplashButton game1 = new SplashButton("star.png", box.getPrefWidth(), box.getPrefHeight());
        SplashButton game2 = new SplashButton("clover.png", box.getPrefWidth(), box.getPrefHeight());
        game1.getStyleClass().add("butt");
        game2.getStyleClass().add("butt");

        box.getChildren().add(game2);
        box.getChildren().add(game1);
        //box.getStyleClass().add("butt");


        game1.setOnMouseClicked(e -> handleLoad());
        game2.setOnMouseClicked(e->selectGame());

        Text instructions= new Text("Load your game!");
        instructions.getStyleClass().add("text");
        instructions.relocate(530, 200);


        root.getChildren().addAll(instructions, box);
        return scene;

    }



   /*public Scene MultiPlayerMode()
   {

       StackPane root = new StackPane();
       var scene = new Scene(root, 1000,600);
       scene.getStylesheets().add(this.getClass().getClassLoader().getResource("splashstyle.css").toExternalForm());
       root.setId("pane");
       SplashButton create = new SplashButton ("creategame.png");
       SplashButton join = new SplashButton ("joingame.png");

       //b.setOnMouseClicked(e -> myStage.setScene(pickGame()));
       Text instructions= new Text(300, 90, "Select if you want to create a new game or join a game");
       //root.getChildren().add(a);
       HBox box = new HBox();
       box.setSpacing(200);
       box.setLayoutX(400);
       box.setLayoutY(300);
       box.getChildren().addAll(create,join);
       root.getChildren().addAll(box,instructions);
       return scene;
   }
*/

   public Scene pickPlayerMode()
   {
       root = new Pane();

       //b.setOnMouseClicked(e -> myStage.setScene(pickGame()));
       Text instructions= new Text("Select single SplashScreen or multiplayer");
       //root.getChildren().add(a);
       HBox box = new HBox();
       box.setSpacing(150);
       box.setLayoutX(400);
       box.setLayoutY(300);
       box.setPrefSize(100,100);
       SplashButton single = new SplashButton ("user.png", box.getPrefWidth(), box.getPrefHeight() );
       SplashButton multi = new SplashButton ("multi.png", box.getPrefWidth(), box.getPrefHeight());
       multi.setOnMouseClicked(e -> myStage.setScene(pickMultiMode()));
       single.setOnMouseClicked(e -> selectGame());
       box.getChildren().addAll(single,multi);
       instructions.relocate(500,200);
       root.getChildren().addAll(box,instructions);
       return scene;

   }


   public Level selectGame()
   {

       Game game = new Game();
       game.loadState(this.getGame());
       var levelState = game.getLevelState();
       Level level = new Level(levelState);
       return level;

   }

    private void handleLoad() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose game");
        fileChooser.setInitialDirectory(new File(getClass().getClassLoader().getResource(GAMEDIR).getFile()));
        File gameFile = fileChooser.showOpenDialog(myStage);
        if (gameFile == null)
            return;
        myGame = gameFile.getPath();
        myStage.setScene(pickPlayerMode());
    }

    public String getGame()
    {
        System.out.println(myGame);
        return myGame;
    }

    public Scene pickMultiMode()
    {

        root = new Pane();

        //b.setOnMouseClicked(e -> myStage.setScene(pickGame()));
        Text instructions= new Text("Select if you want to create a new game or join a game");
        //root.getChildren().add(a);
        HBox box = new HBox();
        box.setSpacing(150);
        box.setLayoutX(400);
        box.setLayoutY(300);
        SplashButton create = new SplashButton ("creategame.png", box.getPrefWidth(), box.getPrefHeight() );
        SplashButton join = new SplashButton ("joingame.png", box.getPrefWidth(), box.getPrefHeight());
        box.getChildren().addAll(create,join);
        instructions.relocate(500,200);
        root.getChildren().addAll(box,instructions);
        return scene;
        //GridPane gp = new GridPane();

        //SplashButton game1 = new SplashButton("star.png", );
        //game1.getStyleClass().add("butt");

        //a.setOnMouseClicked(e -> myStage.setScene(multiPlayer()));
        //SplashButton game2 = new SplashButton("clover.png");
        //game2.getStyleClass().add("butt");
        //gp.setGridLinesVisible(true);


        //box.getChildren().add(game1);
        //box.getChildren().add(game2);
        //gp.setConstraints(box, 3, 3);
        //game1.setLayoutX(400);
        //game1.setLayoutY(300);
        //root.setCenter(box);


}}
