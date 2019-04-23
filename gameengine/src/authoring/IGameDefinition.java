package authoring;

/**
 * Interface to represent the game as the authoring environment should see it
 * @author David Miron
 */
public interface IGameDefinition {

    void setState(IStateDefinition state);
    void saveState(String filename);

}
