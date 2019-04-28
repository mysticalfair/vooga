package Store;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import frontend_objects.CloneableAgentView;

/**
 * @author: Mary Gooneratne
 * @Author: Joanna Li
 * Main item storage for Store
 */
public class StoreItem extends GridPane {
   private static int INDEX_0  = 0;
   private static int INDEX_1 = 1;
   private static String STORE_ITEM_STYLE = "store-item";
   private static String MONEY_ICON_STYLE = "money-icon";
   private static String ITEM_PRICE_STYLE = "item-price";
   private static String ITEM_CONTAINER_STYLE = "item-container";
   private static String ITEM_PRICE_CONTAINER_STYLE = "item-price-container";
   private static String ITEM_PRICE_CONTAINER_BACKGROUND_STYLE = "item-price-container-background";
   private static String ITEM_CONTAINER_BACKGROUND_STYLE = "item-container-background";

   private ImageView icon;
   private StackPane itemContainer;
   private Text itemPrice;
   private StackPane itemPriceContainer;
   //private StoreAgent agent;
   private Rectangle itemPriceContainerBackground;
   private Rectangle itemContainerBackground;


   public StoreItem(){
      super();
      this.icon = new ImageView();
      this.itemPrice = new Text();
      this.itemContainer = new StackPane();
      this.itemPriceContainer = new StackPane();
      this.itemPriceContainerBackground = new Rectangle();
      this.itemContainerBackground = new Rectangle();

      this.setPrice(0);
      this.setUpContainers();
      this.placeChildren();
      // REMOVE line below after testing
      this.setStyles();
   }

   private void setUpContainers(){
      itemPriceContainerBackground.getStyleClass().add(ITEM_PRICE_CONTAINER_BACKGROUND_STYLE);
      itemContainerBackground.getStyleClass().add(ITEM_CONTAINER_BACKGROUND_STYLE);
      this.itemContainer.getChildren().add(itemContainerBackground);
      this.itemPriceContainer.getChildren().addAll(itemPriceContainerBackground, this.itemPrice);

   }
   private void placeChildren(){
      GridPane.setRowIndex(this.itemContainer, INDEX_0);
      GridPane.setColumnIndex(this.itemContainer, INDEX_0);
      GridPane.setRowIndex(this.icon, INDEX_1);
      GridPane.setColumnIndex(this.icon, INDEX_0);
      GridPane.setRowIndex(this.itemPriceContainer, INDEX_1);
      GridPane.setColumnIndex(this.itemPriceContainer, INDEX_1);
      this.getChildren().addAll(this.itemContainerBackground, this.icon, this.itemPriceContainer);
   }

   private void setStyles(){
      this.getStyleClass().add(STORE_ITEM_STYLE);
      this.itemContainer.getStyleClass().add(ITEM_CONTAINER_STYLE);
      this.itemContainerBackground.getStyleClass().add(ITEM_CONTAINER_BACKGROUND_STYLE);
      this.icon.getStyleClass().add(MONEY_ICON_STYLE);
      this.itemPrice.getStyleClass().add(ITEM_PRICE_STYLE);
      this.itemPriceContainerBackground.getStyleClass().add(ITEM_PRICE_CONTAINER_BACKGROUND_STYLE);
      this.itemPriceContainer.getStyleClass().add(ITEM_PRICE_CONTAINER_STYLE);

   }

   private void setPrice(int newPrice){
      this.itemPrice.setText(Integer.toString(newPrice));
   }

}
