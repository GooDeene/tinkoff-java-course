package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class HeartTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        var oldX = point.x();
        var oldY = point.y();
        var sqrt = Math.sqrt(oldX * oldX + oldY * oldY);
        var expression = sqrt * Math.atan(oldY / oldX);
        return new Point(
            sqrt * Math.sin(expression),
            -sqrt * Math.cos(expression)
        );
    }
}
