package engine;

import state.IAgent;

import java.util.List;

public class Game {

    private List<IAgent> agents;



    private void step() {

        for (IAgent agent: agents)
            agent.update(agents);

    }

}
