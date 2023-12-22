package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class PolarTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        var oldX = point.x();
        var oldY = point.y();
        return new Point(
            Math.atan(oldY / oldX) / Math.PI,
            Math.sqrt(oldX * oldX + oldY * oldY) - 1);
    }
}
