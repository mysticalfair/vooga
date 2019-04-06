package state.actiondecision;

import state.agent.IAgent;
import java.util.List;

/**
 * ActionDecision that executes its Action whenever it collides with another agent.
 * @author Jorge Raad
 */
public class OnCollision extends ActionDecision{

    @Override
    public void execute(List<IAgent> agents) {
        IAgent hitAgent = getHitAgent(agents);
        if(hitAgent != null){
            action.execute(hitAgent);
        }
    }

    /**
     * Returns the first agent the baseAgent has collided with. If there is no collision, returns null.
     * @param agents
     * @return agent collided with
     */
    protected IAgent getHitAgent (List<IAgent> agents){
        for(IAgent agent : agents){
            // TODO : Check collision.
//            if(){
//                return agent;
//            }
        }
        return null;
    }
}
