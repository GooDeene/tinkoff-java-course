package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class PowerTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        var oldX = point.x();
        var oldY = point.y();
        var r = Math.sqrt(oldX * oldX + oldY * oldY);
        var theta = Math.atan(oldY / oldX);
        var power = Math.pow(r, Math.sin(theta));
        return new Point(
            power * Math.cos(theta),
            power * Math.sin(theta)
        );
    }
}
