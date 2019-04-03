package state.Objective;



/**
 * @Author:Luke_Truitt
 * Version of Objective passed to Player so they can display a few aspects
 */

public interface IPlayerObjective {
    int getId();
    String getTitle();
    boolean getMet();
}
