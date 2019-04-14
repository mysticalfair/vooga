package engine;

import state.LevelState;
import state.action.IAction;
import state.agent.Agent;

public class DemoGame {
    public static void main(String args[]) {

        LevelState levelState = new LevelState();

        Agent[] agents = levelState.getAgents();

        for(Agent agent : agents) {
            levelState.placeAgent(agent);
        }

        Game game = new Game();
        game.setState(levelState);

        game.run("");
    }
}
