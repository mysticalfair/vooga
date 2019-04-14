package state.action.property;

import state.action.Action;
import state.agent.Agent;

public class AddToPropertyAction extends Action {

    private String propertyName;
    private double amount;

    public AddToPropertyAction(String propertyName, double amount) {
        this.propertyName = propertyName;
        this.amount = amount;
    }

    @Override
    public void execute(Agent agent) throws CloneNotSupportedException {
        if (agent.hasNumberProperty(propertyName)) {
            double current_value = agent.getPropertyValue(propertyName);
            current_value += amount;
            agent.setProperty(propertyName, current_value);
        }
    }
}
