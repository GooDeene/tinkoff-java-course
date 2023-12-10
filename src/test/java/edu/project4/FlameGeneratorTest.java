package edu.project4;

import edu.project4.renderers.ParallelRenderer;
import edu.project4.structures.ImageType;
import edu.project4.structures.Rect;
import edu.project4.transformations.PowerTransformation;
import edu.project4.transformations.SinusTransformation;
import edu.project4.transformations.SphereTransformation;
import edu.project4.transformations.SwirlTransformation;
import edu.project4.transformations.TangTransformation;
import java.nio.file.Path;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class FlameGeneratorTest {

    @Test
    public void flameGeneratorWorksCorectly(@TempDir Path path) {
        var generator = new FlameGenerator(
            1920,
            1080,
            new ParallelRenderer(),
            new Rect(-4, -3, 8, 6),
            List.of(
                new PowerTransformation(),
                new SinusTransformation(),
                new SphereTransformation(),
                new SwirlTransformation(),
                new TangTransformation()
            ),
            new ImageCorrector()
        );

        generator.generate(1000, 1000, 2, path.resolve("img.png"), ImageType.PNG);
        Assertions.assertThat(path.resolve("img.png")).exists();
    }
}
