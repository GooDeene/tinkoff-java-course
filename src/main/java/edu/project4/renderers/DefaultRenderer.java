package edu.project4.renderers;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Rect;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultRenderer extends AbstractRenderer {

    @Override
    public void render(
        FractalImage image,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetrySectorsCount
    ) {
        var allAffineCoeffs = getAffineCoeffsBank(samples);

        for (var i = 0; i < samples; i++) {
            var worldPoint = world.getRandomPoint();

            for (var step = STARTING_ITERATION; step < iterationsPerSample; step++) {
                int randomIndex = ThreadLocalRandom
                    .current()
                    .nextInt(0, allAffineCoeffs.length);

                var coefs = allAffineCoeffs[randomIndex];
                var transformation = transformations.get(
                    ThreadLocalRandom
                        .current()
                        .nextInt(0, transformations.size())
                );

                worldPoint = getAffineTransformedPoint(coefs, worldPoint);
                worldPoint = transformation.apply(worldPoint);

                if (step < 0) {
                    continue;
                }

                var theta = 0d;
                for (var j = 0; j < symmetrySectorsCount; j++) {
                    theta += 2 * Math.PI / symmetrySectorsCount;
                    var rotatedPoint = getRotatedPoint(worldPoint, theta);

                    if (!world.isPointInside(rotatedPoint)) {
                        continue;
                    }

                    var pixel =
                        image.getPixel(
                            (int) ((rotatedPoint.x() - world.x()) * image.getHeight() / world.width()),
                            (int) ((rotatedPoint.y() - world.y()) * image.getHeight() / world.height())
                        );

                    if (pixel != null) {
                        changePixelColor(pixel, coefs);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }
    }
}
