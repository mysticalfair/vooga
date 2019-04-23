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
public class Game implements IGameDefinition {

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
     * @param gameFile String containing file path of the stored game
     */
    public void run(String gameFile) {
        runFlag = true;

//        startup(gameFile);
        double nextTime = System.nanoTime() / nanoTrans;

        while(runFlag) {
            double currentTime = System.nanoTime() / nanoTrans;

            // if deltaTime has passed, then update
            if(currentTime >= nextTime){

                // assign time of next update
                nextTime += DELTA_TIME;
                state.step(DELTA_TIME);
            }

//            else{
//                 // TODO: may change according to how game engine interacts with player
//                 // must convert from seconds to milliseconds
//                int sleepTime = 1000 * (int)(nextTime - currentTime);
//                // game loop should stop until it is time to update again
//                try {
//                    Thread.sleep(sleepTime);
//                } catch (InterruptedException e) {
//                     // TODO: handle this exception
//                }
//            }
        }
    }

//    public IPlayerLevelState getInitial(){
//        LevelState test = new LevelState();
//        List<ActionDecision> def = new ArrayList<>();
//        List<Property> props = new ArrayList<>();
//        Agent agentOrange = new Agent(0, 100,0,10,10,10,"Jamal", "This Image", def, props);
//        test.placeAgent(agentOrange);
//        Level level = new Level(test);
//        this.state.addLevel(level);
//        return test;
//    }

    public IPlayerLevelState getLevelState(){
        return this.state.getLevelState();
    }
    public void startup(String gameFileLocation) {
        loadState(gameFileLocation);
        // TODO: What else must be initialized at startup? If nothing, then delete startup
    }

    private void loadState(String gameFileLocation){
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

    public void step() {
        this.state.step(DELTA_TIME);
    }
    /**
     *
     * @param saveName
     */
    public void saveState(String saveName){
        try {
            serializer.save(state, new File(saveName));
        } catch (SerializationException | IOException e) {
            // TODO: Deal with Exceptions by letting player know about problem.
            e.printStackTrace();
        }
    }

    public void registerPlayer(/* Should probably accept a player somehow*/){

    }

    public void stop(){
        runFlag = false;
    }
}
