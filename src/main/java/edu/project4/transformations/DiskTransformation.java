package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class DiskTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        var oldX = point.x();
        var oldY = point.y();
        var arg = Math.PI * Math.sqrt(oldX * oldX + oldY * oldY);
        var mathExpression = (1 / Math.PI) * Math.atan(oldY / oldX);
        return new Point(mathExpression * Math.sin(arg), mathExpression * Math.cos(arg));
    }
}
