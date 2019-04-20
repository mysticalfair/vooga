package state.action;

import state.IRequiresBaseAgent;
import state.agent.Agent;

import java.util.Map;

public class Spin extends Action implements IRequiresBaseAgent {

    private Agent baseAgent;
    private double angularSpeed;

    public Spin(Map<String, Object> params) {
        super(params);
    }

    @Override
    public void setParams(Map<String, Object> params) {
        this.angularSpeed = (Double) params.get("angularSpeed");
    }

    @Override
    public void injectBaseAgent(Agent agent) {
        this.baseAgent = agent;
    }

    @Override
    public void execute(Agent agent, double deltaTime) throws CloneNotSupportedException {
        baseAgent.setDirection(baseAgent.getDirection() + angularSpeed*deltaTime);
    }
}
