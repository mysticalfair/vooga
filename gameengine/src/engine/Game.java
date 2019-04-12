package engine;

import state.IState;
import state.agent.IAgent;
import state.objective.IObjective;
import state.State;
import utils.SerializationException;
import utils.Serializer;
import utils.serializers.XStreamSerializer;

/**
 * @author Jorge Raad
 * @author Luke Truitt
 * @author David Miron
 * @author Jamie Palka
 */
public class Game implements GameEngineAuthoring {
    public static double nanoTrans = 1000000000.0;
    private boolean runFlag = false;
    private IState state;
    private Serializer serializer;

    public static final double DELTA_TIME = 0.3;

    public void setState(IState state) {
        this.state = state;
        serializer = new XStreamSerializer();
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
                agent.update(state.getMutableAgentsExcludingSelf(agent));
            } catch (CloneNotSupportedException e) {
                // TODO: Deal with exception
                e.printStackTrace();
            }
        }

        for (IObjective objective: state.getMutableObjectives())
            objective.execute(state);

        for (IAgent agent: state.getMutableAgents()) {
            if(agent.isDead()) {
                System.out.println("Agent: " + agent.getName() + " from team " + agent.getTeam());
                state.removeAgent(agent);
                continue;
            }
            double newX = agent.getX() + (agent.getXVelocity() * DELTA_TIME);
            double newY = agent.getY() + (agent.getYVelocity() * DELTA_TIME);
            agent.setLocation(newX, newY);
            System.out.println(agent.getName() + " at " + " X: " + newX + ", Y: " + newY + "\n");
            System.out.println("Health: " + agent.getHealth());
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

    @Override
    public void saveState (IState state) throws SerializationException {
        serializer.serialize((State)state);
    }
}
