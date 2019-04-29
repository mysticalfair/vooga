package authoring;

import java.io.File;

/**
 * Interface to represent the game as the authoring environment should see it
 * @author David Miron
 */
public interface IGameDefinition {

    void setState(IStateDefinition state);
    void saveState(File location);

}
