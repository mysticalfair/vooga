package engine;

import state.IState;

public interface GameEngineAuthoring {

    /**
     * Used by Authoring Environment to save the current state to an XML file through the GameEngine.
     * @param state
     */
    public void saveState(IState state);
}
