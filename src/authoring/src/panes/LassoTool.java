package panes;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Resources used:
 *  https://stackoverflow.com/questions/40629841/circle-not-visible-as-i-drag-the-mouse-in-javafx
 */

public class LassoTool {

    private Ellipse ellipse;
    private MapPane target;
    private Scene mainScene;

    private double startingPosX, startingPosY;

    public LassoTool(MapPane target){
        initEllipse();

        this.target = target;
        //this.mainScene = mainScene;
    }

    public void setMouseActions(Scene scene){
        mainScene = scene;
        mainScene.setOnMouseClicked(e -> mousePressed(e));
        mainScene.setOnMouseDragged(e -> mouseDragged(e));
        mainScene.setOnMouseReleased(e -> mouseReleased(e));
    }

    private void initEllipse(){
        ellipse = new Ellipse();
        ellipse.setStrokeWidth(1.0);
        ellipse.setFill(Color.TRANSPARENT);
        ellipse.setStroke(Color.BLACK);
        ellipse.getStrokeDashArray().addAll(3.0);
    }

    private void removeMouseActions(){
        mainScene.setOnMouseClicked(null);
        mainScene.setOnMouseDragged(null);
        mainScene.setOnMouseReleased(null);
    }

    private void mousePressed(MouseEvent event){
        initEllipse();
        startingPosX = event.getX();
        startingPosY = event.getY();
        ellipse.setCenterX(startingPosX);
        ellipse.setCenterY(startingPosY);
        ellipse.setRadiusX(0);
        ellipse.setRadiusY(0);
        target.getContentChildren().add(ellipse);
        mainScene.setOnMouseReleased(e -> mouseReleased(e));
    }

    private void mouseReleased(MouseEvent event){
        target.selectAgents(ellipse);
        target.getContentChildren().remove(ellipse);
        removeMouseActions();
    }

    private void mouseDragged(MouseEvent event){
        target.getContentChildren().remove(ellipse);
        ellipse.setCenterX((event.getX() + startingPosX) / 2);
        ellipse.setCenterY((event.getY() + startingPosY) / 2);
        ellipse.setRadiusX(Math.abs((event.getX() - startingPosX) / 2));
        ellipse.setRadiusY(Math.abs((event.getY() - startingPosY) / 2));
        target.getContentChildren().add(ellipse);
    }

}
