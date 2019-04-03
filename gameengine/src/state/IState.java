package state;

import java.util.List;

/**
 * @author David Miron
 * @author Jamie Palka
 */
public interface IState {
    int getHealth();
    List<IObjective> getObjectives();
    List<IAgent> getImmutableAgents();
}
