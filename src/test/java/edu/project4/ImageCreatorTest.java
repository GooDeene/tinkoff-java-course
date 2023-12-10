package edu.project4;

import edu.project4.structures.FractalImage;
import edu.project4.structures.ImageType;
import java.nio.file.Path;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ImageCreatorTest {

    @Test
    public void imageCreatorCreateImageCorrectly(@TempDir Path path) {
        var path1 = path.resolve("img.png");
        ImageCreator.save(
            FractalImage.create(400, 400),
            path.resolve("img.png"),
            ImageType.PNG
        );

        Assertions.assertThat(path.resolve("img.png")).exists();
    }
}
