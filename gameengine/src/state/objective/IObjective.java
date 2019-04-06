package state.objective;

import state.IPlayerState;

/**
 * @Author:Luke_Truitt
 * Extension of Objective that Engine and Author have access to.
 */
public interface IObjective extends IPlayerObjective {

    void setId(int id);
    // Set the title of the objective
    void setTitle(String title);

    // Set whether or no the objective has been met
    void setMet(boolean met);

    // Set whether or not the objective has been met.
    void execute(IPlayerState state);
}
