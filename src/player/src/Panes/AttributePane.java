package Panes;

import Attribute.AttributeView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import state.attribute.Attribute;

import java.util.List;

/**
 * @author: Mary Gooneratne, Joanna Li
 * Game scene pane displaying player Attributes (e.g. money, health)
 */

public class AttributePane extends VBox {
   private static String ATTRIBUTE_STYLE = "attribute-pane";
   private static int INIT_ATTRIBUTE = 0;

   private ImageView healthBar;
   //private Text scoreText = new Text();
   //private Text moneyText = new Text();
   //Starts with 0 now but will update when Listeners are added
   private LongProperty score = new SimpleLongProperty(0);
   private LongProperty money = new SimpleLongProperty(0);

   private int health;

   public AttributePane(){
      super();
      this.healthBar = new HealthBar();
      this.getStyleClass().add(ATTRIBUTE_STYLE);
      this.setHealth(INIT_ATTRIBUTE);
      //scoreText.textProperty().bind(Bindings.createStringBinding(() -> ("Current: " + score.get())));
      //moneyText.textProperty().bind(Bindings.createStringBinding(() -> ("Money: " + money.get())));
      this.getChildren().addAll(this.healthBar);
   }


   protected void setHealth(int newHealth){
      this.health = newHealth;
   }

   public void addAttribute(AttributeView attribute){

      this.getChildren().add(attribute);
   }

   public void addAttributes(List<AttributeView> attributes){
            this.getChildren().addAll(attributes);
   }
   protected LongProperty getScoreProperty()
   {
      return score;
   }

   protected LongProperty getMoneyProperty()
   {
      return money;
   }

}
