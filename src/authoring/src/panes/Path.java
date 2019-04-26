package panes;

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
    private boolean draggable, removable;

    public Path(){
        points = new ArrayList<>();
        lines = new ArrayList<>();
        draggable = false;
        removable = false;
    }

    public List<Circle> getPoints(){
        var list = new ArrayList<Circle>();
        for(PathPoint p: points){
            list.add((Circle) p.getPoint());
        }
        return list;
    }

    public List<Line> getLines(){
        return lines;
    }

    public void toggleDraggable(){
        draggable = !draggable;
        for(PathPoint point: points){
            point.toggleDragMode();
        }
    }

    public void toggleRemovable(){
        removable = !removable;
        for(PathPoint point: points){
            point.toggleRemoveMode();
        }
    }

    public Circle addPoint(double x, double y){
        var point = new PathPoint(x, y);
        var visual = point.getPoint();
        points.add(point);
        return (Circle) visual;
    }

    public void removePoint(PathPoint point){
        if(point != null){
            points.remove(point);
        }
    }

    public PathPoint getPoint(Circle visual){
        for(PathPoint point: points){
            var pointVisual = (Circle) point.getPoint();
            if(pointVisual == visual){
                return point;
            }
        }
        return null;
    }

    public List<Line> updateLines(){
        lines = new ArrayList<>();

        int pointsCount = points.size();
        if(pointsCount <= MINIMUM_SIZE){
            return List.of();
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
        return lines;
    }
}
