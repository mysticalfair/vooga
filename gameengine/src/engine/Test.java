package engine;

import engine.Game;
import state.State;
import state.action.IAction;
import state.action.PointToAgent;
import state.actiondecision.ActionDecision;
import state.agent.Agent;
import state.condition.CollisionCondition;
import state.condition.Condition;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String args[]) {

        State state = new State();
        Agent agent = new Agent(0, 0, 0, "Jorge", "Bad Guys", 50, 10, 10, 5, 90, 10);

        Agent agent2 = new Agent(0, 100, 100, "Luke", "Good Guys", 50, 10, 10, 0, 90, 20);
        IAction action = new PointToAgent();
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new CollisionCondition(agent2));
        ActionDecision actionDecision = new ActionDecision(action, conditions);

        agent2.addActionDecisionRaw(actionDecision);

        state.defineAgent(agent);
        state.placeAgent(agent);
        state.placeAgent(agent2);


        Game game = new Game();
        game.setState(state);

        game.run("");
    }

}
