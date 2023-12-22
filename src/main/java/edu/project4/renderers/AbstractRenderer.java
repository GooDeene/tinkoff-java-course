package edu.project4.renderers;

import edu.project4.interfaces.Renderer;
import edu.project4.structures.AffineCoefficients;
import edu.project4.structures.Pixel;
import edu.project4.structures.Point;
import java.awt.Color;

public abstract class AbstractRenderer implements Renderer {

    protected static final int STARTING_ITERATION = -20;

    protected Point getRotatedPoint(Point point, double theta) {
        var xRot = point.x() * Math.cos(theta) - point.y() * Math.sin(theta);
        var yRot = point.x() * Math.sin(theta) + point.y() * Math.cos(theta);
        return new Point(xRot, yRot);
    }

    protected void changePixelColor(Pixel pixel, AffineCoefficients coef) {
        if (pixel.getHitCount() == 0) {
            pixel.setColor(coef.color());
        } else {
            pixel.setColor(
                new Color(
                    (pixel.getColor().getRed() + coef.color().getRed()) / 2,
                    (pixel.getColor().getGreen() + coef.color().getGreen()) / 2,
                    (pixel.getColor().getBlue() + coef.color().getBlue()) / 2
                )
            );
        }
    }

    protected AffineCoefficients[] getAffineCoeffsBank(int samples) {
        var transformations = new AffineCoefficients[samples];
        for (var i = 0; i < samples; i++) {
            transformations[i] = AffineCoefficients.create();
        }
        return transformations;
    }

    protected Point getAffineTransformedPoint(AffineCoefficients coef, Point point) {
        return new Point(
            coef.a() * point.x()
                + coef.b() * point.y()
                + coef.c(),
            coef.d() * point.x()
                + coef.e() * point.y()
                + coef.f()
        );
    }
}
