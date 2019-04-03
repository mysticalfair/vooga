package state;

import java.util.List;

public interface IStateAuthoring extends IState {
    List<IObjective> getObjectives();
}
