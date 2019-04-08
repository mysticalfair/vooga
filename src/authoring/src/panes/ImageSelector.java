package panes;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Purpose of class: provide simple method call for a main class to set BackgroundImage by upload
 *
 * References:
 *  https://stackoverflow.com/questions/41927994/open-image-from-filechooser-in-javafx
 *  https://teamtreehouse.com/community/setting-selected-image-file-to-imageview-in-javafx
 */

public class ImageSelector {

    public Image getUserImage(){
        var fileChooser = new FileChooser();
        // TODO: Change to properties file
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files","*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String imagePath = file.toURI().toString();
            Image image = new Image(imagePath);
            //ImageView view = new ImageView(image);
            return image;
            //BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
            //BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            //return backgroundImage;
        }
        else{
            nullFileAlert();
            return getUserImage();
        }
    }

    private void nullFileAlert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Please Select a File");
        alert.setContentText("You didn't select a file!");
        alert.showAndWait();
    }
}
