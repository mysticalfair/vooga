package engine;

import state.LevelState;
import state.action.IAction;
import state.agent.IAgent;

public class DemoGame {
    public static void main(String args[]) {

        LevelState levelState = new LevelState();

        IAgent[] agents = levelState.getAgents();

        for(IAgent agent : agents) {
            levelState.placeAgent(agent);
        }

        Game game = new Game();
        game.setState(levelState);

        game.run("");
    }
}
