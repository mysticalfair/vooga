package engine;

import state.State;
import state.action.IAction;
import state.action.PointToAgent;
import state.action.collision.MeleeOnCollision;
import state.actiondecision.ActionDecision;
import state.agent.Agent;
import state.condition.CollisionCondition;
import state.condition.Condition;

import java.util.ArrayList;
import java.util.List;

public class DemoGame {
    public static void main(String args[]) {

        State state = new State();
        Agent towerAgent = new Agent(0, 0, 0, "Jorge", 50, 10, 10, 5, 90, 10);

        Agent meleeAgent = new Agent(0, 100, 100, "Jorge", 50, 10, 10, 0, 90, 20);
        IAction action = new MeleeOnCollision();
        List<Condition> conditions = new ArrayList<>();
        conditions.add(new CollisionCondition(meleeAgent));
        ActionDecision actionDecision = new ActionDecision(action, conditions);

        meleeAgent.addActionDecisionRaw(actionDecision);

        state.defineAgent(towerAgent);
        state.placeAgent(towerAgent);
        state.placeAgent(meleeAgent);


        Game game = new Game();
        game.setState(state);

        game.run("");
    }
}
