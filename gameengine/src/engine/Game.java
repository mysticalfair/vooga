package engine;

import engine.event.GameEventMaster;
import gameengine.IGameDefinition;
import gameengine.ILevelDefinition;
import state.IRequiresGameEventMaster;
import state.IState;
import state.action.IAction;
import state.action.collision.MeleeOnCollision;
import state.actiondecision.ActionDecision;
import state.agent.Agent;
import state.agent.IAgent;
import state.condition.CollisionCondition;
import state.condition.Condition;
import state.objective.IObjective;
import state.State;

import java.util.ArrayList;
import java.util.List;
//import utils.SerializationException;
//import utils.Serializer;
//import utils.serializers.XStreamSerializer;

/**
 * @author Jorge Raad
 * @author Luke Truitt
 * @author David Miron
 * @author Jamie Palka
 */
public class Game implements IGameDefinition {

    private static final int START_LEVEL = 1;

    public static double nanoTrans = 1000000000.0;
    private boolean runFlag = false;
    public static final double DELTA_TIME = 0.0167;
    //private Serializer serializer;

    private List<Level> levels;
    private int currentLevel;

    /**
     * The list of current levels levels
     * @return The levels
     */
    @Override
    public List<? extends ILevelDefinition> getLevels() {
        return this.levels;
    }

    /**
     * Remove a level
     * @param index The index of the level
     */
    @Override
    public void removeLevel(int index) {
        if (levels.size() > index) {
            levels.remove(index);
        }
    }

    @Override
    public void addLevel(ILevelDefinition level) {
        levels.add((Level) level);
    }

    public Game() {
        this.levels = new ArrayList<>();
        this.currentLevel = START_LEVEL;
    }

    /**
     * Once called, begins continually running the game until stop() is called on it.
     * @param gameFile String containing file path of the stored game
     */
    public void run(String gameFile) {
        runFlag = true;

        startup();
        double nextTime = System.nanoTime() / nanoTrans;

        while(runFlag) {
            double currentTime = System.nanoTime() / nanoTrans;

            // if deltaTime has passed, then update
            if(currentTime >= nextTime){

                // assign time of next update
                nextTime += DELTA_TIME;
                levels.get(currentLevel).step();
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
        // Levels = ...
    }

    public void stop(){
        runFlag = false;
    }

//    @Override
//    public void saveState (IState state) throws SerializationException {
//        //serializer.serialize((State)state);
//    }
}
