package edu.project4.transformations;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.Point;

public class CylinderTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.y()), point.y());
    }
}
