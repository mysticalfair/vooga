package panes;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.util.function.Consumer;

public class AuthoringUtil {

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

}
