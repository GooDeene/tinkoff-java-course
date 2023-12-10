package edu.project4;

import edu.project4.renderers.DefaultRenderer;
import edu.project4.renderers.ParallelRenderer;
import edu.project4.structures.FractalImage;
import edu.project4.structures.Rect;
import edu.project4.transformations.CylinderTransformation;
import edu.project4.transformations.DiskTransformation;
import edu.project4.transformations.SinusTransformation;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RendererTest {

    @Test
    public void defaultRendererWorksCorrectlyInCorrectConditions() {
        Assertions.assertDoesNotThrow(() ->
            new DefaultRenderer().render(
                FractalImage.create(400, 800),
                new Rect(-4, -3, 8, 6),
                List.of(
                    new CylinderTransformation(),
                    new DiskTransformation(),
                    new SinusTransformation()
                ),
                10, 3000, 3
            )
        );
    }

    @Test
    public void parallelRendererWorksCorrectlyInCorrectConditions() {
        Assertions.assertDoesNotThrow(() ->
            new ParallelRenderer().render(
                FractalImage.create(200, 400),
                new Rect(-4, -3, 8, 6),
                List.of(
                    new CylinderTransformation(),
                    new DiskTransformation(),
                    new SinusTransformation()
                ),
                20, 5000, 2
            )
        );
    }
}
