package Panes;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * @author: Mary Gooneratne
 * Game scene pane displaying player Attributes (e.g. money, health)
 */

public class AttributePane extends VBox {
   private static String ATTRIBUTE_STYLE = "attrribute-pane";
   private static int INIT_ATTRIBUTE = 0;

   private ImageView healthBar;
   private int score;
   private int money;
   private int health;

   public AttributePane(){
      super();
      this.healthBar = new HealthBar();
      this.getChildren().add(this.healthBar);
      this.getStyleClass().add(ATTRIBUTE_STYLE);
      this.setHealth(INIT_ATTRIBUTE);
   }

   private void setScore(int newScore){
      this.score = newScore;
   }

   private void setMoney(int newMoney){
      this.money = newMoney;
   }

   private void setHealth(int newHealth){
      this.health = newHealth;
   }

}
