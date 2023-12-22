package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class SphereTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        var oldX = point.x();
        var oldY = point.y();
        return new Point(
            oldX / (oldX * oldX + oldY * oldY),
            oldY / (oldX * oldX + oldY * oldY));
    }
}
