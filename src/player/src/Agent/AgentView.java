package Agent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import state.agent.IPlayerAgent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author: Mary Gooneratne
 * Frontend object for Agents
 */
public class AgentView extends ImageView implements PropertyChangeListener {

   private static String AGENT_STYLE = "demo-agent";
   private double health;
   private double direction;
   private String url;

   public AgentView(IPlayerAgent playerAgent){
      super();
      this.init(playerAgent);
      this.getStyleClass().add(AGENT_STYLE);
      playerAgent.addPropertyChangeListener(this);
      System.out.println("AGENT VIEW: " + this.getX());
   }

   public void init(IPlayerAgent playerAgent){
      this.setX(playerAgent.getX() - (playerAgent.getWidth()/2));
      this.setY(playerAgent.getY() - (playerAgent.getHeight()/2));
      // TODO: Make sure coordinate systems align
      this.setFitHeight(playerAgent.getHeight());
      this.setFitWidth(playerAgent.getWidth());
      this.setDirection(playerAgent.getDirection());
      this.setImage(new Image(this.getClass().getClassLoader().getResourceAsStream(playerAgent.getImageURL())));
      this.url = playerAgent.getImageURL();
   }

   private void setDirection(double direction) {
      this.direction = direction;
   }

   public void propertyChange(PropertyChangeEvent e) {
      System.out.println("property changed");
      if(e.getPropertyName().equals("x")) {
         this.setLayoutX((Double) e.getNewValue());
         System.out.println("*****" + this.url + ": "+ "X- "+ this.getX() + "********");
      } else if(e.getPropertyName().equals("y")) {
         this.setY((Double) e.getNewValue());
         System.out.println("Changed Y: "+ e.getNewValue());
      } else if(e.getPropertyName().equals("imageUrl")) {
         this.setImage(new Image((String) e.getNewValue()));
         System.out.println("Changed Image");
      } else if(e.getPropertyName().equals("width")) {
         this.setFitWidth((Double) e.getNewValue());
         System.out.println("Changed Width");
      } else if(e.getPropertyName().equals("height")) {
         this.setFitHeight((Double) e.getNewValue());
         System.out.println("Changed Height");
      } else if(e.getPropertyName().equals("direction")) {
         this.setDirection((Double) e.getNewValue());
         System.out.println("Changed Direction");
      }

   }
}