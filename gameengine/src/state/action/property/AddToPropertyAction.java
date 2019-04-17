package state.action.property;

import state.action.Action;
import state.agent.Agent;

import java.util.Map;

public class AddToPropertyAction extends Action {

    private String propertyName;
    private double amount;

    public AddToPropertyAction(Map<String, ? extends Object> params) {
        super(params);
    }

    public void setParams(Map<String, ? extends Object> params) {
        this.propertyName = (String)params.get("propertyName");
        this.amount = (Double)params.get("amount");
    }

    @Override
    public void execute(Agent agent, double deltaTime) throws CloneNotSupportedException {
        // TODO: implement agent.getPropertyValue(propertyName), agent.setProperty(propertyName, current_value)
//        if (agent.hasNumberProperty(propertyName)) {
//            double current_value = agent.getPropertyValue(propertyName);
//            current_value += amount;
//            agent.setProperty(propertyName, current_value);
//        }
    }
}
