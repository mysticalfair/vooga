import engine.Game;
import state.State;
import state.agent.Agent;

public class Test {

    public static void main(String args[]) {

        State state = new State();
        Agent agent = new Agent();

        state.defineAgent(agent);


        Game game = new Game();
        game.setState(state);

        game.run("", 0.0167);

    }

}
