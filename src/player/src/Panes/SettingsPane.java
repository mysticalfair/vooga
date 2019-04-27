package Panes;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * @author: Mary Gooneratne
 * Pane displayed upon press of settings button
 */
public class SettingsPane extends GridPane {
   private static String HOME_STYLE = "home-button";
   private static String INFO_STYLE = "info-button";
   private static String OPEN_STYLE = "open-button";
   private static String PLAY_STYLE = "play-button";
   private static String RESTART_STYLE = "restart-button";
   private static String SAVE_STYLE = "save-button";
   private static String SETTINGS_PANE_STYLE = "settings-pane";

   private static int INDEX_0 = 0;
   private static int INDEX_1 = 1;
   private static int INDEX_2 = 2;

   private Button homeButton;
   private Button infoButton;
   private Button openButton;
   private Button playButton;
   private Button restartButton;
   private Button saveButton;

   public SettingsPane() {
      super();
      this.initButtons();
      this.setStyles();
   }

   private void initButtons(){
      this.homeButton = new Button();
      this.infoButton = new Button();
      this.openButton = new Button();
      this.playButton = new Button();
      this.restartButton = new Button();
      this.saveButton = new Button();
      this.placeButtons();
   }

   private void placeButtons(){
      GridPane.setRowIndex(this.homeButton, INDEX_0);
      GridPane.setColumnIndex(this.homeButton, INDEX_0);
      GridPane.setRowIndex(this.infoButton, INDEX_1);
      GridPane.setColumnIndex(this.infoButton, INDEX_2);
      GridPane.setRowIndex(this.openButton, INDEX_1);
      GridPane.setColumnIndex(this.openButton, INDEX_1);
      GridPane.setRowIndex(this.playButton, INDEX_0);
      GridPane.setColumnIndex(this.playButton, INDEX_1);
      GridPane.setRowIndex(this.restartButton, INDEX_0);
      GridPane.setColumnIndex(this.restartButton, INDEX_2);
      GridPane.setRowIndex(this.saveButton, INDEX_1);
      GridPane.setColumnIndex(this.saveButton, INDEX_0);
      this.getChildren().addAll(this.homeButton, this.playButton, this.infoButton, this.openButton, this.restartButton, this.saveButton);
   }

   private void setStyles(){
      this.homeButton.getStyleClass().add(HOME_STYLE);
      this.infoButton.getStyleClass().add(INFO_STYLE);
      this.openButton.getStyleClass().add(OPEN_STYLE);
      this.playButton.getStyleClass().add(PLAY_STYLE);
      this.restartButton.getStyleClass().add(RESTART_STYLE);
      this.saveButton.getStyleClass().add(SAVE_STYLE);
      this.getStyleClass().add(SETTINGS_PANE_STYLE);
   }
}
