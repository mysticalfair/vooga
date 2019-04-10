package engine;

import state.IState;
import state.agent.IAgent;
import state.objective.IObjective;
import state.State;

/**
 * @author Jorge Raad
 * @author Luke Truitt
 * @author David Miron
 * @author Jamie Palka
 */
public class Game {
    public static double nanoTrans = 1000000000.0;
    private boolean runFlag = false;
    private IState state;

    public static final double DELTA_TIME = 0.0167;

    public void setState(IState state) {
        this.state = state;
    }

    /**
     * Once called, begins continually running the game until stop() is called on it.
     * @param gameFile String containing file path of the stored game
     */
    public void run(String gameFile) {
        runFlag = true;
        startup();
        double nextTime = System.nanoTime()/nanoTrans;
        while(runFlag){
            double currentTime = System.nanoTime()/nanoTrans;

            // if deltaTime has passed, then update
            if(currentTime >= nextTime){
                // assign time of next update
                nextTime += DELTA_TIME;
                step();
            }
            //else{
                // TODO: may change according to how game engine interacts with player
                // must convert from seconds to milliseconds
                //int sleepTime = 1000 * (int)(nextTime - currentTime);
                //game loop should stop until it is time to update again
                //try {
                 //   Thread.sleep(sleepTime);
                //} catch (InterruptedException e) {
                    // TODO: handle this exception
                //}
            //}
        }

    }

    private void startup() {
        // TODO: deserialize a state from a file
        // state = **
    }

    private void step() {

        for (IAgent agent: state.getMutableAgents()) {
            try {
                agent.update(state.getMutableAgents());
            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            }
        }

        for (IObjective objective: state.getMutableObjectives())
            objective.execute(state);

        for (IAgent agent: state.getMutableAgents()) {
            double newX = agent.getX() + (agent.getXVelocity() * DELTA_TIME);
            double newY = agent.getY() + (agent.getYVelocity() * DELTA_TIME);
            agent.setLocation(newX, newY);
            System.out.println("X: " + newX + "\nY: " + newY + "\n");
        }

        sendState();

    }

    /**
     * Send the state to the Player
     */
    private void sendState() {
        // TODO DO
    }

    public void stop(){
        runFlag = false;
    }

}
