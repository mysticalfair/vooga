package authoring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {

    public static void main(String[] args) {

        System.out.println("Hello");

        try {
            GameFactory g = new GameFactory();

            List<AvailableAction> actions = g.getAvailableActions();

            Map<String, Object> cparams = new HashMap<>();
            cparams.put("angle", 5.0);
            cparams.put("speed", 3);
            IActionDefinition actionDefinition = g.createAction("MoveAtRelativeAngle", cparams);

            System.out.println("Here");
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
