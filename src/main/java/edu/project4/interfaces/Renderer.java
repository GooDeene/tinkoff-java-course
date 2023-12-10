package edu.project4.interfaces;

import edu.project4.structures.FractalImage;
import edu.project4.structures.Rect;
import java.util.List;

public interface Renderer {

    void render(
        FractalImage image,
        Rect world,
        List<Transformation> transformations,
        int samples,
        int iterationsPerSample,
        int symmetrySectorsCount
    ) throws InterruptedException;
}
