package Agent;

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
   private int id;
   private String image;
   private double x;
   private double y;
   private double health;
   private boolean isAttacking;

   public AgentView(IPlayerAgent playerAgent){
      super();
      this.update(playerAgent);
      this.getStyleClass().add(AGENT_STYLE);
      this.playerAgent.addPropertyChangeListener
   }

   public void update(IPlayerAgent playerAgent){
   }
   public void propertyChange(PropertyChangeEvent e) {
      String propertyName = e.getPropertyName();
   }
}