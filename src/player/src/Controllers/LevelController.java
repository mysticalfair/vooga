package Controllers;

import Panes.AttributePane;
import Panes.MapPane;
import Store.StorePane;
import state.IPlayerLevelState;
import state.agent.IPlayerAgent;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class LevelController implements PropertyChangeListener {
   MapController mapController;
   AttributeController attributeController;
   StoreController storeController;

   public LevelController(){
      this.mapController = new MapController();
      this.attributeController = new AttributeController();
      this.storeController = new StoreController();
   }

   public LevelController(IPlayerLevelState state){
      this.mapController = new MapController(state.getImmutableAgents());
      this.attributeController = new AttributeController(state.getImmutableAttributes());
      this.storeController = new StoreController(state.getImmutableOptions());
      state.addPropertyChangeListener(this);
   }

   public MapPane getMapPane(){
      return this.mapController.getPane();
   }

   public AttributePane getAttributePane(){
      return this.attributeController.getPane();
   }

   public StorePane getStorePane(){
      return this.storeController.getPane();
   }

   public void propertyChange(PropertyChangeEvent e){
      System.out.println("A PROPERTY CHANGE EVENT");
      if(e.getPropertyName().equals("CurrentAgent")){
         System.out.println("adding new agents BABYYYY");
         this.mapController.clearAgents();
         this.mapController.addAgents((List<IPlayerAgent>)(e.getNewValue()));
      }
   }
}
