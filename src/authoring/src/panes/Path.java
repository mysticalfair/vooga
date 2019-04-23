package panes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class Path {

    public static final int FIRST_POINT = 0;
    public static final int MINIMUM_SIZE = 1;

    public static final List<Double> DASH_ARRAY = List.of(2d, 4d);

    public static final Color LINE_COLOR = Color.BLUE;

    /**
     * This object tracks the order and number of points added to a given path.
     * On map click, a point is added to the path at the location of the click.
     * When an existing point is clicked and dragged, rather than adding a new point, the existing point is moved
     *  Set circle's onMouseClick and onMouseDrag to be like a draggable image
     */

    // TODO: add tool/way for removing path points

    private List<PathPoint> points;
    private List<Line> lines;

    public Path(){
        points = new ArrayList<>();
        lines = new ArrayList<>();
    }

    public void addPoint(Group root, double x, double y){
        var point = new PathPoint(x, y);
        var visual = point.getPoint();
        visual.layoutXProperty().addListener(e -> updateLines(root));
        visual.layoutYProperty().addListener(e -> updateLines(root));
        points.add(point);
        root.getChildren().add(visual);
        updateLines(root);
    }

    public void removePoint(Group root, Circle visual){
        for(PathPoint point: points){
            var pointVisual = (Circle) point.getPoint();
            if(pointVisual == visual){
                root.getChildren().remove(visual);
                points.remove(point);
                updateLines(root);
            }
        }
    }

    private void updateLines(Group root){
        root.getChildren().removeAll(lines);
        lines = new ArrayList<>();

        int pointsCount = points.size();
        if(pointsCount <= MINIMUM_SIZE){
            return;
        }

        Circle currentCircle = (Circle) points.get(FIRST_POINT).getPoint();
        Circle previousCircle;

        for(int i=FIRST_POINT + 1; i<pointsCount; i++){
            previousCircle = currentCircle;
            currentCircle = (Circle) points.get(i).getPoint();
            var line = new Line(currentCircle.getCenterX(), currentCircle.getCenterY(), previousCircle.getCenterX(), previousCircle.getCenterY());
            line.getStrokeDashArray().addAll(DASH_ARRAY);
            line.setStroke(LINE_COLOR);
            lines.add(line);
        }
        root.getChildren().addAll(lines);
    }
}
