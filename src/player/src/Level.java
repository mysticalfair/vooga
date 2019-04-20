import Panes.AttributePane;
import Panes.MapPane;
import Panes.SettingsButton;
import Panes.SettingsPane;
import Store.StoreButton;
import Store.StorePane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import state.IPlayerLevelState;
import state.agent.IPlayerAgent;

import java.awt.*;
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

   private AttributePane attributePane;
   private SettingsButton settingsButton;
   private StoreButton storeButton;
   private SettingsPane settingsPane;
   private StorePane storePane;
   private MapPane mapPane;

   private List<IPlayerAgent> fakeAgents;
   private Button initDemoButton;
   private Button updateDemoButton;

   public Level(IPlayerLevelState levelState){
      super(new BorderPane(), HEIGHT, WIDTH);
      this.setRoot();
      this.initializeButtons();
      this.initializePanes();
      this.setStyles();
      this.mapPane.addAgents(levelState.getImmutableAgents());
   }

   private void setRoot(){
      this.root = (BorderPane)this.getRoot();
   }

   private void initializeButtons(){
      this.settingsButton = new SettingsButton();
      this.storeButton = new StoreButton();
      /*
       * For purpose of demo
       */
      this.updateDemoButton = new Button("UPDATE DEMO");
      this.initDemoButton = new Button("INITIALIZE DEMO");

      this.initializeButtonActions();
      this.root.setRight(this.rightPane);

   }

   private void initializePanes(){
      this.leftPane = new VBox();
      this.rightPane = new VBox();
      this.centerPane = new VBox();
      this.bottomPane = new VBox();

      this.attributePane = new AttributePane();
      this.settingsPane = new SettingsPane();
      this.mapPane = new MapPane();
      this.storePane = new StorePane();

      this.placePanes();
   }


   private void placePanes(){
      this.leftPane.getChildren().add(this.attributePane);
      this.centerPane.getChildren().add(this.mapPane);
      this.rightPane.getChildren().addAll(this.settingsButton, this.storeButton, this.updateDemoButton, this.initDemoButton);

      this.root.setLeft(this.leftPane);
      this.root.setRight(this.rightPane);
      this.root.setCenter(this.centerPane);
      this.root.setBottom(this.bottomPane);
   }

   private void setStyles(){
      this.getStylesheets().add(getClass().getResource(GAME_CSS).toExternalForm());
      this.getStylesheets().add(FONT);
      this.centerPane.getStyleClass().add(CENTER_PANE_STYLE);
   }

   private void initializeButtonActions(){
      this.settingsButton.setOnAction(e -> toggleSettingsPane());
      this.storeButton.setOnAction(e -> toggleStorePane());
      this.initDemoButton.setOnAction(e -> initDemo());
      this.updateDemoButton.setOnAction(e -> updateDemo());

   }

   private void toggleSettingsPane(){
      if(this.centerPane.getChildren().contains(this.settingsPane)){
         this.centerPane.getChildren().remove(this.settingsPane);
         this.centerPane.getChildren().add(this.mapPane);
      }
      else {
         this.centerPane.getChildren().remove(this.mapPane);
         this.centerPane.getChildren().add(this.settingsPane);
      }
   }

   private void toggleStorePane(){
      if(this.bottomPane.getChildren().contains(this.storePane)){
         this.bottomPane.getChildren().remove(this.storePane);
      }
      else {
         this.bottomPane.getChildren().add(this.storePane);
      }
   }

   /*
      For purpose of demo
    */

   private void initDemo() {

   }

   private void updateDemo(){
   }


}
