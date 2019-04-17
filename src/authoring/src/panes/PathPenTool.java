package panes;

import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class PathPenTool {

    private MapPane target;
    private boolean penEnabled;
    private Circle previousCircle = null;
    private Circle currentCircle = null;
    // TODO: replace with list of linkedlists

    public PathPenTool(MapPane target){
        this.target = target;
        penEnabled = false;
    }

    public void setMouseActions(Scene scene){
        //Image image = new Image("Pen.png");
        //scene.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() /2));

        target.accessContainer(node -> node.setOnMouseClicked(e -> mousePressed(e)));
        target.accessContainer(node -> node.setOnMouseDragged(e -> mouseDragged(e)));
        target.accessContainer(node -> node.setOnMouseReleased(e -> mouseReleased(e)));
    }

    public void togglePenSelect(Scene scene){
        penEnabled = !penEnabled;
        if(penEnabled){
            spawnPen(scene);
            setMouseActions(scene);
        }
        else{
            eliminatePen(scene);
            removeMouseActions();
        }
    }

    private void spawnPen(Scene scene){
        Image image = new Image(ToolbarPane.PEN_IMAGE);
        scene.setCursor(new ImageCursor(image, image.getWidth() / 2, image.getHeight() /2));
    }

    private void eliminatePen(Scene scene){
        scene.setCursor(Cursor.DEFAULT);
    }

    private void removeMouseActions(){
        target.accessContainer(node -> node.setOnMouseClicked(null));
        target.accessContainer(node -> node.setOnMouseDragged(null));
        target.accessContainer(node -> node.setOnMouseReleased(null));
    }

    private void mousePressed(MouseEvent event){
        if(!penEnabled){
            return;
        }
        currentCircle = new Circle(event.getX(), event.getY(), 10, Color.BLACK);
        target.getContentChildren().add(currentCircle);
        if(previousCircle != null) {
            Line l = new Line(currentCircle.getCenterX(), currentCircle.getCenterY(), previousCircle.getCenterX(), previousCircle.getCenterY());
            target.getContentChildren().add(l);
        }
        previousCircle = currentCircle;
    }

    private void mouseReleased(MouseEvent event){

    }

    private void mouseDragged(MouseEvent event){

    }
}
