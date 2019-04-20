package Agent;

import state.agent.IPlayerAgent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class AgentController {
   private List<AgentView> agentViews;

   public AgentController() {
      this.agentViews =new ArrayList<>();
   }

   public AgentController(List<IPlayerAgent> playerAgents){
      this.agentViews = new ArrayList<>();
      this.addAgents(playerAgents);
   }

   public AgentView addAgent(IPlayerAgent playerAgent){
      AgentView agentView = new AgentView(playerAgent);
      playerAgent.addPropertyChangeListener(new PropertyChangeListener() {
         @Override
         public void propertyChange(PropertyChangeEvent evt) {

         }
      });
      this.agentViews.add(agentView);
      return agentView;
   }

   public List<AgentView> addAgents(List<IPlayerAgent> playerAgents){
      List<AgentView> views = new ArrayList<>();
      for(IPlayerAgent playerAgent: playerAgents){
         views.add(this.addAgent(playerAgent));
      }
      return views;
   }
}