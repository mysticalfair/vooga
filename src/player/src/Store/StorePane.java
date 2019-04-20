package Store;

import javafx.scene.layout.VBox;
import state.agent.PlayerAgent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mary Gooneratne
 * Pane showing store after store button pressed
 */

public class StorePane extends VBox{
   private List<PlayerAgent> engineInventory;
   private List<StoreItem> storeInventory;

   public StorePane(){
      this.engineInventory = new ArrayList<PlayerAgent>();
   }

   public void update(List<PlayerAgent> newInventory){
      this.engineInventory = newInventory;
   }
}
