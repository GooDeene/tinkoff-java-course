package edu.project4.renderers;

import edu.project4.interfaces.Transformation;
import edu.project4.structures.AffineCoefficients;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Rect;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ParallelRenderer extends AbstractRenderer {

    private static final int THREADS_COUNT = 16;

    @Override
    public void render(
        FractalImage image,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetrySectorsCount
    ) throws InterruptedException {
        var allAffineCoeffs = getAffineCoeffsBank(samples);
        var executorService = Executors.newFixedThreadPool(THREADS_COUNT);

        for (var i = 0; i < samples; i++) {
            executorService.execute(() -> executeIterations(
                image,
                world,
                iterationsPerSample,
                symmetrySectorsCount,
                transformations,
                allAffineCoeffs
            ));
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
    }

    private void executeIterations(
        FractalImage canvas,
        Rect world,
        int iterationsPerSample,
        int symmetrySectorsCount,
        List<Transformation> transformations,
        AffineCoefficients[] coefficientsArray
    ) {
        var worldPoint = world.getRandomPoint();
        for (var i = STARTING_ITERATION; i < iterationsPerSample; i++) {
            var randomIndex = ThreadLocalRandom
                .current()
                .nextInt(0, coefficientsArray.length);
            var coefs = coefficientsArray[randomIndex];
            var transformation = transformations.get(
                ThreadLocalRandom.current()
                    .nextInt(0, transformations.size())
            );

            worldPoint = getAffineTransformedPoint(coefs, worldPoint);
            worldPoint = transformation.apply(worldPoint);

            if (i < 0) {
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
                    canvas.getPixel(
                        (int) ((rotatedPoint.x() - world.x()) * canvas.getWidth() / world.width()),
                        (int) ((rotatedPoint.y() - world.y()) * canvas.getHeight() / world.height())
                    );
                if (pixel != null) {
                    synchronized (pixel) {
                        changePixelColor(pixel, coefs);
                        pixel.setHitCount(pixel.getHitCount() + 1);
                    }
                }
            }
        }
    }
}
