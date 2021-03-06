package state.action.property;

import authoring.exception.PropertyDoesNotExistException;
import state.action.Action;
import state.agent.Agent;

import java.util.Map;

public class AddToPropertyAction extends Action {

    private String propertyName;
    private double amount;

    public AddToPropertyAction(Map<String, Object> params) {
        super(params);
    }

    public void setParams(Map<String, Object> params) {
        this.propertyName = (String)params.get("targetPropertyName");
        this.amount = (Double)params.get("amount");
    }

    @Override
    public void execute(Agent agent, double deltaTime) throws CloneNotSupportedException {
        try{
            double current_value = (double) agent.getPropertyValue(propertyName);
            current_value += amount;
            agent.setProperty(propertyName, current_value);
        }
        catch(PropertyDoesNotExistException e) {
            // No such property, so do nothing
            // TODO: probably throw an exception here
        }
    }
}
