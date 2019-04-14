package engine;

import state.State;
import state.action.IAction;
import state.action.PointToAgent;
import state.action.collision.MeleeOnCollision;
import state.action.collision.ShooterOnCollision;
import state.actiondecision.ActionDecision;
import state.agent.Agent;
import state.agent.IAgent;
import state.condition.CollisionCondition;
import state.condition.Condition;
import state.condition.RangeCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DemoGame {
    public static void main(String args[]) {

        State state = new State();

        IAgent[] agents = state.getAgents();

        for(IAgent agent : agents) {
            state.placeAgent(agent);
        }

        Game game = new Game();
        game.setState(state);

        game.run("");
    }
}
