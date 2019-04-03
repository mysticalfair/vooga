package state;

import java.util.List;

/**
 * @author David Miron
 * @author Jamie Palka
 */
public interface IStateAuthoring extends IState {
    List<IObjective> getObjectives();
}
