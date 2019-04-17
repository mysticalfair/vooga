package authoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {

    public static void main(String[] args) {

        System.out.println("Hello");

        try {
            GameFactory g = new GameFactory();

            List<AvailableAction> actions = g.getAvailableActions();

            Map<String, Object> aparams = new HashMap<>();
            aparams.put("angle", 5.0);
            aparams.put("speed", 3);
            IActionDefinition actionDefinition = g.createAction("MoveAtRelativeAngle", aparams);

            Map<String, Object> cparams = new HashMap<>();
            cparams.put("interval", 3.0);
            IConditionDefinition conditionDefinition = g.createCondition("Interval", cparams);
            List<IConditionDefinition> conditions = new ArrayList<>();
            conditions.add(conditionDefinition);

            IActionDecisionDefinition ad = g.createActionDecision(actionDefinition, conditions);


            System.out.println("Here");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
