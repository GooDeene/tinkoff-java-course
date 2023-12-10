package edu.project4;

import edu.project4.structures.FractalImage;
import edu.project4.structures.ImageType;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageCreator {
    private ImageCreator() {
    }

    public static void save(FractalImage image, Path fileName, ImageType imageFormat) {
        var bufferedImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (var x = 0; x < image.getWidth(); x++) {
            for (var y = 0; y < image.getHeight(); y++) {
                var pixel = image.getPixel(x, y);
                if (pixel != null) {
                    bufferedImage.setRGB(x, y, pixel.getColor().getRGB());
                }
            }
        }
        try {
            ImageIO.write(bufferedImage, imageFormat.name(), fileName.toFile());
        } catch (IOException e) {
            throw new IllegalArgumentException("Не получилось создать файл с изображением фрактала!");
        }

    }
}
