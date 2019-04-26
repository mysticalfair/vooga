package engine;

import authoring.IActionDefinition;
import authoring.IGameDefinition;
import authoring.IStateDefinition;
import state.IPlayerLevelState;
import state.LevelState;
import state.Property;
import state.State;
import state.actiondecision.ActionDecision;
import state.agent.Agent;
import utils.SerializationException;
import utils.Serializer;
import utils.SerializerSingleton;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Jorge Raad
 * @author Luke Truitt
 * @author David Miron
 * @author Jamie Palka
 */
public class Game implements IGameDefinition, IPlayerGame {

    public static double nanoTrans = 1000000000.0;
    private boolean runFlag = false;
    public static final double DELTA_TIME = 0.0167;
    private Serializer serializer;

    private State state;

    public Game() {
        serializer = SerializerSingleton.getInstance();
    }

    /**
     * Once called, begins continually running the game until stop() is called on it.
     */
    @Deprecated
    public void run() {
        runFlag = true;

        if(state.getGameOverStatus()) { runFlag = false; }

        // TODO: throw exception if state not initialized?
        double nextTime = System.nanoTime() / nanoTrans;

        while(runFlag) {
            double currentTime = System.nanoTime() / nanoTrans;

            // if deltaTime has passed, then update
            if(currentTime >= nextTime){

                // assign time of next update
                nextTime += DELTA_TIME;
                state.step(DELTA_TIME);
            }
        }
    }

    @Override
    public IPlayerLevelState getLevelState(){
        return this.state.getLevelState();
    }

    /**
     * @param gameFileLocation String containing file path of the stored game
     */
    @Override
    public void loadState(String gameFileLocation){
        try {
            state = (State) serializer.load(new File(gameFileLocation));
        } catch (SerializationException | IOException e) {
            // TODO: Deal with Exceptions by letting player know invalid file was chosen.
            e.printStackTrace();
        }
    }

    @Override
    public void setState(IStateDefinition state) {
        this.state = (State)state;
    }

    @Override
    public void step() {
        this.state.step(DELTA_TIME);
    }

    @Override
    public void saveState(String saveName){
        try {
            serializer.save(state, new File(saveName));
        } catch (SerializationException | IOException e) {
            // TODO: Deal with Exceptions by letting player know about problem.
            e.printStackTrace();
        }
    }

    @Override
    public void stop(){
        runFlag = false;
    }
}
