package engine;

import state.IPlayerLevelState;

/**
 * This interface is to be implemented by Game, giving player the methods necessary to run the game.
 * @author Jorge Raad
 */
public interface IPlayerGame {

    /**
     * Loads the desired State from the given XML file into the Game.
     * This is to be called by Player to load and display the game prior to running.
     * @param gameFileLocation
     */
    void loadState(String gameFileLocation);

    /**
     * Saves the current State held by the game into an XML file of the given name.
     * @param saveName
     */
    void saveState(String saveName);

    /**
     * Returns the current LevelState so that the player may take the current configuration of the level
     * (such as current agents) and display them in the scene.
     * @return
     */
    IPlayerLevelState getLevelState();

//    /**
//     * Initiates the running of the game loop.
//     */
//    void run();

    /**
     * Halts the running of the game loop.
     */
    void stop();


    /**
     * Updates the game's state. To be called from within in the Player's own JavaFX step method
     */
    void step();
}
