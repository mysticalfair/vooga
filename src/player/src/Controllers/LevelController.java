package Controllers;
/**
 * Controller class to affect the Level
 * @author Joanna Li
 * @author Mary G
 * @author Luke Truitt
 */

import Panes.AttributePane;
import Panes.MapPane;
import Store.StorePane;
import state.IPlayerLevelState;
import state.agent.IPlayerAgent;
import state.attribute.IPlayerAttribute;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
      if(e.getPropertyName().equals("Add Agent")){
         this.mapController.addAgent((IPlayerAgent)(e.getNewValue()));
      }
      else if (e.getPropertyName().equals("Remove Agent"))
      {
         this.mapController.removeAgent((IPlayerAgent)(e.getOldValue()));
      }
      else if (e.getPropertyName().equals("Add Attribute"))
      {
         this.attributeController.addAttribute((IPlayerAttribute)(e.getNewValue()));
      }
      else if (e.getPropertyName().equals("Game Over"))
      {

      }
   }
}
