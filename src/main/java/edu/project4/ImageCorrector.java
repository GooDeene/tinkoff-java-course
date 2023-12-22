package edu.project4;

import edu.project4.interfaces.ImageProcessor;
import edu.project4.structures.FractalImage;
import java.awt.Color;

public class ImageCorrector implements ImageProcessor {

    private static final double GAMMA = 1.1;

    @Override
    public void correct(FractalImage image) {
        var max = 0d;
        for (var x = 0; x < image.getWidth(); x++) {
            for (var y = 0; y < image.getHeight(); y++) {
                var pixel = image.getPixel(x, y);
                if (pixel != null && pixel.getHitCount() != 0) {
                    pixel.setNormal(Math.log10(pixel.getHitCount()));
                    max = Math.max(max, pixel.getNormal());
                }
            }
        }

        for (var x = 0; x < image.getWidth(); x++) {
            for (var y = 0; y < image.getHeight(); y++) {
                var pixel = image.getPixel(x, y);
                if (pixel == null) {
                    continue;
                }

                pixel.setNormal(pixel.getNormal() / max);
                pixel.setColor(new Color(
                        (int) (pixel.getColor().getRed() * Math.pow(pixel.getNormal(), (1.0 / GAMMA))),
                        (int) (pixel.getColor().getGreen() * Math.pow(pixel.getNormal(), (1.0 / GAMMA))),
                        (int) (pixel.getColor().getBlue() * Math.pow(pixel.getNormal(), (1.0 / GAMMA)))
                    )
                );
            }
        }
    }
}
