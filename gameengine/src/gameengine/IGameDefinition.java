package gameengine;

import java.util.List;

/**
 * Interface to represent the game as the authoring environment should see it
 * @author David Miron
 */
public interface IGameDefinition {

    List<ILevelDefinition> getLevels();
    void removeLevel(int index);
    void addLevel(ILevelDefinition level);

}
