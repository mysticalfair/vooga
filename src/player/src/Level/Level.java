package Level;

import Attribute.AttributeView;
import Controllers.LevelController;
import Panes.AttributePane;
import Panes.MapPane;
import Panes.SettingsButton;
import Panes.SettingsPane;
import Store.StoreButton;
import Store.StorePane;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import state.IPlayerLevelState;
import state.attribute.IPlayerAttribute;

import java.util.ArrayList;
import java.util.List;

public class Level extends Scene {
   private static int WIDTH = 600;
   private static int HEIGHT = 1000;
   private static String GAME_CSS = "game.css";
   private static String FONT = "https://fonts.googleapis.com/css?family=Allerta+Stencil";
   private static String CENTER_PANE_STYLE = "center-pane";

   private BorderPane root;
   private VBox rightPane;
   private VBox leftPane;
   private VBox centerPane;
   private VBox bottomPane;

   private SettingsButton settingsButton;
   private StoreButton storeButton;
   private SettingsPane settingsPane;

   private LevelController levelController;

   private List<AttributeView> attributes;

   public Level(IPlayerLevelState levelState){
      super(new BorderPane(), HEIGHT, WIDTH);
      this.setRoot();
      this.levelController = new LevelController(levelState);


      this.settingsPane = new SettingsPane();
      this.initializeButtons();
      this.initializePanes();
      this.setStyles();

      this.attributes = new ArrayList<>();
      for(IPlayerAttribute attribute : levelState.getImmutableAttributes()) {
         this.attributes.add(new AttributeView(attribute));
      }
   }

   private void setRoot(){
      this.root = (BorderPane)this.getRoot();
   }

   private void initializeButtons(){
      this.settingsButton = new SettingsButton();
      this.storeButton = new StoreButton();
      this.initializeButtonActions();
   }

   private void initializePanes(){
      this.leftPane = new VBox();
      this.rightPane = new VBox();
      this.centerPane = new VBox();
      this.bottomPane = new VBox();

      this.placePanes();
   }

   private void placePanes(){
      this.leftPane.getChildren().add(this.levelController.getAttributePane());
      this.centerPane.getChildren().add(this.levelController.getMapPane());
      this.rightPane.getChildren().addAll(this.settingsButton, this.storeButton);

      this.root.setLeft(this.leftPane);
      this.root.setRight(this.rightPane);
      this.root.setCenter(this.centerPane);
      this.root.setBottom(this.bottomPane);
   }

   private void setStyles(){
//      this.getStylesheets().add(getClass().getResource(GAME_CSS).toExternalForm());
      this.getStylesheets().add(GAME_CSS);
      this.getStylesheets().add(FONT);
      this.centerPane.getStyleClass().add(CENTER_PANE_STYLE);
   }

   private void initializeButtonActions(){
      this.settingsButton.setOnAction(e -> toggleSettingsPane());
      this.storeButton.setOnAction(e -> toggleStorePane());

   }

   private void toggleSettingsPane(){
      if(this.centerPane.getChildren().contains(this.settingsPane)){
         this.centerPane.getChildren().remove(this.settingsPane);
         this.centerPane.getChildren().add(this.levelController.getMapPane());
      }
      else {
         this.centerPane.getChildren().add(this.settingsPane);
         this.centerPane.getChildren().remove(this.levelController.getMapPane());
      }
   }

   private void toggleStorePane(){
      if(this.bottomPane.getChildren().contains(this.levelController.getStorePane())){
         this.bottomPane.getChildren().remove(this.levelController.getStorePane());
      }
      else {
         this.bottomPane.getChildren().add(this.levelController.getStorePane());
      }
   }


}
