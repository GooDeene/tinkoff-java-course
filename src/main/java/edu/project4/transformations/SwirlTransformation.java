package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class SwirlTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        var oldX = point.x();
        var oldY = point.y();
        var squareRadius = oldX * oldX + oldY * oldY;
        return new Point(
            oldX * Math.sin(squareRadius) - oldY * Math.cos(squareRadius),
            oldX * Math.cos(squareRadius) + oldY * Math.sin(squareRadius)
        );
    }
}
