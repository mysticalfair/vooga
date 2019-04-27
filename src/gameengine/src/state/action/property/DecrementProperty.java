package state.action.property;

import state.action.Action;
import state.agent.Agent;

import java.util.Map;

/**
 * @Author:Luke_truitt
 */
public class DecrementProperty extends Action {
    private String propertyName;
    private double amount;

    public DecrementProperty(Map<String, Object> params) {
        super(params);
    }

    public void setParams(Map<String, Object> params) {
        this.propertyName = (String)params.get("property");
        this.amount = (Double)params.get("value");
    }

    @Override
    public void execute(Agent agent, double deltaTime) throws CloneNotSupportedException {
        try{
            double current_value = (double) agent.getProperty(propertyName);
            current_value -= amount;
            agent.setProperty(propertyName, current_value);
        }
        catch(NullPointerException e) {
            // No such property, so do nothing
            // TODO: probably throw an exception here
        }
    }
}
