package engine;

import state.Agent.IAgentComplete;
import state.Objective.IObjectiveComplete;
import state.StateComplete;
// Major Source: http://entropyinteractive.com/2011/02/game-engine-design-the-game-loop/
/**
 * @Author Jorge Raad
 * @Author Luke Truitt
 * @author David Miron
 * @author Jamie Palka
 */
public class Game {

    private boolean runFlag = false;
    private StateComplete state;

    public void run(String gameFile, double deltaTime){
        runFlag = true;
        startup();
        double nextTime = System.nanoTime()/1000000000.0;
        while(runFlag){
            double currentTime = System.nanoTime()/1000000000.0;

            // if deltaTime has passed, then update
            if(currentTime >= nextTime){
                // assign time of next update
                nextTime += deltaTime;
                step();
            }
            else{
                // TODO: may change according to how game engine interacts with player
                // must convert from seconds to milliseconds
                int sleepTime = 1000 * (int)(nextTime - currentTime);
                //game loop should stop until it is time to update again
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // TODO: handle this exception
                }
            }
        }

    }

    private void startup() {
        // TODO: deserialize a state from a file
        // state = **
    }

    private void step() {

        for (IAgentComplete agent: state.getMutableAgents())
            agent.update(state.getMutableAgents());

        for (IObjectiveComplete objective: state.getMutableObjectives())
            objective.execute(state);

        sendState();

    }

    /**
     * Send the state to the Player
     */
    private void sendState() {
        // TODO
    }

    public void stop(){
        runFlag = false;
    }

}
