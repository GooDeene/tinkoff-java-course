package edu.project4.structures;

import java.awt.Color;

public final class FractalImage {

    private final Pixel[][] pixelsField;
    private final int height;
    private final int width;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    private FractalImage(Pixel[][] data, int height, int width) {
        this.pixelsField = data;
        this.height = height;
        this.width = width;
    }

    public static FractalImage create(int width, int height) {
        Pixel[][] data = new Pixel[height][width];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = new Pixel(Color.BLACK, 0, 0);
            }
        }
        return new FractalImage(data, height, width);
    }

    public Pixel getPixel(int x, int y) {
        if (!containsPixel(x, y)) {
            return null;
        }
        return pixelsField[y][x];
    }

    public boolean containsPixel(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}
