package state.action.property;

import authoring.exception.PropertyDoesNotExistException;
import state.IRequiresBaseAgent;
import state.action.Action;
import state.agent.Agent;

import java.util.Map;

/**
 * @Author:Luke_Truitt
 */
public class DecrementProperty extends PropertyAction {

    private String propertyName;
    private double amount;

    public DecrementProperty(Map<String, Object> params) {
        super(params);
    }

    public void setParams(Map<String, Object> params) {
        this.propertyName = (String)params.get("propertyName");
        this.amount = (Double)params.get("value");
        this.onBaseAgent = (Boolean)params.get("onBaseAgent");
    }

    @Override
    public void execute(Agent agent, double deltaTime) {
        try{
            if(this.onBaseAgent) {
                double current_value = (double) this.baseAgent.getProperty(propertyName);
                current_value -= amount;
                this.baseAgent.setProperty(propertyName, current_value);
                System.out.println("DAMAGE! HP: " + current_value);
            } else {
                double current_value = (double) agent.getProperty(propertyName);
                current_value -= amount;
                agent.setProperty(propertyName, current_value);
                System.out.println("DAMAGE! HP: " + current_value);
            }
        }
        catch(PropertyDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
