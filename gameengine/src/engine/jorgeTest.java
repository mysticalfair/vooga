package engine;

import state.IState;
import state.State;
import state.action.IAction;
import state.action.PointToAgent;
import state.actiondecision.ActionDecision;
import state.agent.Agent;
import state.condition.CollisionCondition;
import state.condition.Condition;
//import utils.SerializationException;

import java.util.ArrayList;
import java.util.List;

public class jorgeTest {
    public static void main(String[] args){

        State state = new State();
//        Agent agent = new Agent(0, 0, 0, "Jorge", 50, 10, 10, 5, 90);
//
//        Agent agent2 = new Agent(0, 100, 100, "Jorge", 50, 10, 10, 0, 90);
//        IAction action = new PointToAgent();
//        List<Condition> conditions = new ArrayList<>();
//        conditions.add(new CollisionCondition(agent2));
//        ActionDecision actionDecision = new ActionDecision(action, conditions);
//
//        agent2.addActionDecisionRaw(actionDecision);
//
//        state.defineAgent(agent);
//        state.placeAgent(agent);
//        state.placeAgent(agent2);
//

//        Game game = new Game();
//        game.setState(state);
//        try {
//            game.saveState(state);
//        } catch (SerializationException e) {
//            throw new RuntimeException();
//        }
    }
}
