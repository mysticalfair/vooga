package state.Objective;

import state.IStateComplete;

/**
 * @Author:Luke_Truitt
 * Extension of Objective that Engine and Author have access to.
 */
public interface IObjectiveComplete extends IObjective {
    // Set the title of the objective
    void setTitle(String title);

    // Convert to player version
    IObjective toImmutable();

    // Set whether or not the objective has been met.
    void execute(IStateComplete state);
}
