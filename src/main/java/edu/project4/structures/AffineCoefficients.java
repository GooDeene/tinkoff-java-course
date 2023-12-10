package edu.project4.structures;

import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

public record AffineCoefficients(double a, double b, double c, double d, double e, double f, Color color) {

    private static final int MAX_COLOR_VALUE = 256;

    public static AffineCoefficients create() {
        while (true) {
            var a = ThreadLocalRandom.current().nextDouble(-1, 1);
            var b = ThreadLocalRandom.current().nextDouble(-1, 1);
            var c = ThreadLocalRandom.current().nextDouble(-1, 1);
            var d = ThreadLocalRandom.current().nextDouble(-1, 1);
            var e = ThreadLocalRandom.current().nextDouble(-1, 1);
            var f = ThreadLocalRandom.current().nextDouble(-1, 1);
            if (isValid(a, b, d, e)) {
                return new AffineCoefficients(a, b, c, d, e, f, getRandomColor());
            }
        }
    }

    private static boolean isValid(double a, double b, double d, double e) {
        return (a * a + d * d < 1)
            && (b * b + e * e < 1)
            && (a * a + b * b + d * d + e * e < 1 + (a * e - b * d) * (a * e - b * d));
    }

    private static Color getRandomColor() {
        return new Color(
            ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE),
            ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE),
            ThreadLocalRandom.current().nextInt(0, MAX_COLOR_VALUE)
        );
    }
}
