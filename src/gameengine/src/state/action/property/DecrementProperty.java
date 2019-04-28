package state.action.property;

import authoring.exception.PropertyDoesNotExistException;
import state.action.Action;
import state.agent.Agent;

import java.util.Map;

/**
 * @Author:Luke_Truitt
 */
public class DecrementProperty extends Action {

    private String propertyName;
    private double amount;

    public DecrementProperty(Map<String, Object> params) {
        super(params);
    }

    public void setParams(Map<String, Object> params) {
        this.propertyName = (String)params.get("propertyName");
        this.amount = (Double)params.get("amount");
    }

    @Override
    public void execute(Agent agent, double deltaTime) {
        try{
            double current_value = (double) agent.getProperty(propertyName);
            current_value -= amount;
            agent.setProperty(propertyName, current_value);
        }
        catch(PropertyDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
