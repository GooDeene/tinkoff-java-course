package edu.project4;

import edu.project4.interfaces.ImageProcessor;
import edu.project4.interfaces.Renderer;
import edu.project4.interfaces.Transformation;
import edu.project4.structures.FractalImage;
import edu.project4.structures.ImageType;
import edu.project4.structures.Rect;
import java.nio.file.Path;
import java.util.List;

public final class FlameGenerator {

    private final FractalImage fractalImage;
    private final Renderer renderer;
    private final Rect world;
    private final List<Transformation> transformations;
    private final ImageProcessor processor;

    public FlameGenerator(
        int width,
        int height,
        Renderer renderer,
        Rect world,
        List<Transformation> transformations,
        ImageProcessor processor
    ) {
        this.fractalImage = FractalImage.create(width, height);
        this.renderer = renderer;
        this.world = world;
        this.transformations = transformations;
        this.processor = processor;
    }

    public void generate(
        int samples,
        int iterationsPerSample,
        int symmetrySectorsCount,
        Path path,
        ImageType format
    ) {
        try {
            renderer.render(fractalImage, world, transformations, samples, iterationsPerSample, symmetrySectorsCount);
            processor.correct(fractalImage);
            ImageCreator.save(fractalImage, path, format);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
