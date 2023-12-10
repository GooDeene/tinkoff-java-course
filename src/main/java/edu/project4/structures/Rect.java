package edu.project4.structures;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, double width, double height) {
    public Point getRandomPoint() {
        double xCoord = ThreadLocalRandom.current().nextDouble(0, width);
        double yCoord = ThreadLocalRandom.current().nextDouble(0, height);
        return new Point(xCoord, yCoord);
    }

    public boolean isPointInside(Point point) {
        return point.x() >= x
            && point.x() < width + x
            && point.y() >= y
            && point.y() < height + y;
    }
}
