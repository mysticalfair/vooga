package state.action.property;

import authoring.exception.PropertyDoesNotExistException;
import state.action.Action;
import state.agent.Agent;

import java.util.Map;

/**
 * @author Jorge Raad
 */
public class IncrementProperty extends PropertyAction {

    private String propertyName;
    private double amount;

    public IncrementProperty(Map<String, Object> params) {
        super(params);
    }

    public void setParams(Map<String, Object> params) {
        this.propertyName = (String)params.get("property");
        this.amount = (Double)params.get("value");
        this.onBaseAgent = (Boolean)params.get("onBaseAgent");
    }

    @Override
    public void execute(Agent agent, double deltaTime) throws PropertyDoesNotExistException {
        try{
            if(this.onBaseAgent) {
                double current_value = (double) this.baseAgent.getProperty(propertyName);
                current_value += amount;
                this.baseAgent.setProperty(propertyName, current_value);
            } else {
                double current_value = (double) agent.getProperty(propertyName);
                current_value += amount;
                agent.setProperty(propertyName, current_value);
            }
        }
        catch(PropertyDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
