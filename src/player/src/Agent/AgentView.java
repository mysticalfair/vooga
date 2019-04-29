package Agent;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import state.agent.IPlayerAgent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author: Mary Gooneratne
 * @author: Joanna Li
 * @author: Luke_Truitt
 * Frontend object for Agents
 */
public class AgentView extends ImageView implements PropertyChangeListener {

   private static String AGENT_STYLE = "demo-agent";
   private double health;
   private double direction;
   private String url;

   private static int count = 0;
   private int selfCount = 0;

   private String listen;


   public AgentView(IPlayerAgent playerAgent){
      super();
      this.init(playerAgent);
      this.getStyleClass().add(AGENT_STYLE);
      playerAgent.addPropertyChangeListener(this);

      System.out.println("AGENT VIEW: " + this.getX());
      selfCount = count;
      count++;

      this.listen = playerAgent.toString();
      System.out.println("CREATING A NEW AGENT VIEW THAT IS LISTENING TO THIS OBJECT: " + playerAgent);

   }

   public void init(IPlayerAgent playerAgent){
      this.setX(playerAgent.getX());
      this.setY(playerAgent.getY() + (playerAgent.getHeight()));
      // TODO: Make sure coordinate systems align
      this.setFitHeight(playerAgent.getHeight());
      this.setFitWidth(playerAgent.getWidth());
      this.setRotate(playerAgent.getDirection());
      this.setImage(new Image("file:" + playerAgent.getImageURL()));

      //this.setImage(new Image(AgentView.class.getResourceAsStream(playerAgent.getImageURL())));
      //System.out.println(playerAgent.getImageURL());
      //System.out.println(this.class.)
      //System.out.println(AgentView.class.getResourceAsStream(playerAgent.getImageURL()));
      this.url = playerAgent.getImageURL();
   }


   public void propertyChange(PropertyChangeEvent e) {
      if (e.getPropertyName().equals("x")) {
         this.setX((Double) e.getNewValue());


      } else if(e.getPropertyName().equals("y")) {
         this.setY((Double) e.getNewValue());
      } else if(e.getPropertyName().equals("imageUrl")) {
         this.setImage(new Image((String) e.getNewValue()));
      } else if(e.getPropertyName().equals("width")) {
         this.setFitWidth((Double) e.getNewValue());
      } else if(e.getPropertyName().equals("height")) {
         this.setFitHeight((Double) e.getNewValue());
      } else if(e.getPropertyName().equals("direction")) {
         this.setRotate((Double) e.getNewValue());
      }

   }

   public String getUrl(){
      return this.url;
   }

   public String getListen(){
      return this.listen;
   }
}