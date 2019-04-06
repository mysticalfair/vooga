package state.action;

import state.agent.IAgent;

public abstract class Action implements IAction {

    public abstract void execute(IAgent agent);

}
