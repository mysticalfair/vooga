package Panes;

import Agent.AgentController;
import Agent.AgentView;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import state.agent.IPlayerAgent;

import java.util.List;

public class MapPane extends StackPane {
   private static double WIDTH = 1000;
   private static double HEIGHT = 1000;

   private Canvas myBackgroundCanvas;
   private AgentController agentController;
   private double myHeight;
   private double myWidth;

   public MapPane(){
      super();
      this.myHeight = HEIGHT;
      this.myWidth = WIDTH;
      this.initBackground();
      this.agentController = new AgentController();
   }

   public void addAgent(IPlayerAgent playerAgent){
      this.getChildren().add(
         this.agentController.addAgent(playerAgent));
   }

   public void addAgents(Iterable<IPlayerAgent> agents){
      for(IPlayerAgent a: agents){
         this.addAgent(a);
      }
   }

   private void initBackground(){
      this.myBackgroundCanvas = new Canvas(myWidth,myHeight);
      this.getChildren().add(myBackgroundCanvas);
   }

}