package util;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.function.Consumer;

/**
 * Utility methods for the Authoring Environment
 *
 * @author Samuel Rabinowitz
 * @author Mary Stuart Elder
 */
public class AuthoringUtil {

    /**
     * Common image extensions for using a file chooser.
     * @author Samuel Rabinowitz
     */
    public static final String[] IMAGE_EXTENSIONS = {"*.jpg", "*.gif", "*.jpeg", "*.bmp", "*.png"};

    /**
     * Open the default system file chooser for opening or saving, and get the resulting file.
     * @param fileTypeName the name of the type of file in plain English ("Document", "Image", "Zip Archive", etc.)
     * @param extensions the valid file extensions for this type of file (e.g. "*.jpg", "*.gif", "*.jpeg", "*.bmp" would work for image).
     * @param save true if this will be a save operation, false if this will be an open operation
     * @param window the JavaFX window to which to tie this file chooser (can be null)
     * @param actionOnCompletion the action that runs on successful opening or saving of the file
     * @param actionOnNull the action that runs if the opened or saved file is null
     *
     * @author Samuel Rabinwoitz
     */
    public static void openFileChooser(String fileTypeName, String[] extensions, boolean save, Window window, Consumer<File> actionOnCompletion, Runnable actionOnNull) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(fileTypeName, extensions));
        File file;
        if (save) {
            file = fc.showSaveDialog(window);
        } else {
            file = fc.showOpenDialog(window);
        }
        if (file != null) {
            actionOnCompletion.accept(file);
        }
        else {
            actionOnNull.run();
        }
    }

    /**
     * Create a button that has an image instead of text.
     * @param buttonImageName the file URL for the image
     * @param buttonSize the width and height of the button, in pixels
     * @param buttonImageSize the width and height of the button's image, in pixels
     * @param action the action for the button to execute on click
     * @return the newly-created button
     *
     * @author Mary Stuart Elder
     * javadoc @author Samuel Rabinowitz
     */
    public static Button createSquareImageButton(String buttonImageName, double buttonSize, double buttonImageSize, EventHandler action){
        var button = new Button();
        var image = new ImageView(new Image(buttonImageName));
        image.setFitWidth(buttonSize);
        image.setFitHeight(buttonSize);
        button.setGraphic(image);
        button.setPrefSize(buttonImageSize, buttonImageSize);
        button.setOnAction(action);
        return button;
    }

}
