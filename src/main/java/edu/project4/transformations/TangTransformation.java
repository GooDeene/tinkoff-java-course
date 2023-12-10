package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class TangTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        var oldX = point.x();
        var oldY = point.y();
        return new Point(Math.sin(oldX) / Math.cos(oldY), Math.tan(oldY));
    }
}
