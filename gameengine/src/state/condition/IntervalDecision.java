package state.condition;

import state.action.IAction;
import state.actiondecision.ActionDecision;

/**
 * ActionDecision type to run an action at a specific interval
 * @author David Miron
 */
public abstract class IntervalDecision extends ActionDecision {

    private double intervalMillis;
    private long lastOccurence;

    /**
     * Create an IntervalDecision
     * @param interval How often to run the action, in seconds
     * @param action The action to run at this interval
     */
    public IntervalDecision(double interval, IAction action) {
        this.intervalMillis = interval * 1000;
        this.action = action;
    }

    protected boolean intervalHasPassed() {
        return System.currentTimeMillis() - lastOccurence > intervalMillis;
    }

    protected void resetInterval() {
        lastOccurence = System.currentTimeMillis();
    }
}
