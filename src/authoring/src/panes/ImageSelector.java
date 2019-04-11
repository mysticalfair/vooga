package panes;

import javafx.scene.image.Image;
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
        Image image = null;
        if (file != null) {
            String imagePath = file.toURI().toString();
            image = new Image(imagePath);
        }
        return image;
    }
}
