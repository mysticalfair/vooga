package Controllers;

import Panes.AttributePane;
import javafx.scene.layout.Pane;
import state.attribute.IPlayerAttribute;

import java.util.ArrayList;
import java.util.List;

public class AttributeController {
   List<IPlayerAttribute> attributes;
   AttributePane attributePane;

   public AttributeController(){
      this.attributePane = new AttributePane();
      this.attributes = new ArrayList<>();
   }

   public AttributeController(List<IPlayerAttribute> attributes) {
      this.attributes = attributes;
      this.attributePane = new AttributePane();
   }

   public AttributePane getPane(){
      return this.attributePane;
   }

}
