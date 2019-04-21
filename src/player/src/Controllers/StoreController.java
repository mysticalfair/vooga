package Controllers;

import Store.StorePane;
import state.agent.IPlayerAgent;

import java.util.List;

public class StoreController {
   private StorePane storePane;

   public StoreController(){
      this.storePane = new StorePane();
   }
   public StoreController(List<IPlayerAgent> options){
      this.storePane = new StorePane(options);
   }

   public StorePane getPane(){
      return this.storePane;
   }
}
