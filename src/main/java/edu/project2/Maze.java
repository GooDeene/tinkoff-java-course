package edu.project2;

import java.util.Arrays;
import java.util.Objects;

public final class Maze {
    private static final int MAX_MAZE_WIDTH = 700;
    private static final int MAX_MAZE_HEIGHT = 700;
    private final int height;
    private final int width;
    private Cell[][] field;

    public Maze(int height, int width) {
        if (!isMazeSizesValid(height, width)) {
            throw new IllegalArgumentException("Рамзер лабиринта должен быть больше 2 x 2, но меньше %s x %s"
                .formatted(MAX_MAZE_WIDTH, MAX_MAZE_HEIGHT));
        }
        this.width = width;
        this.height = height;
        initializeMazeField();
    }

    public boolean isCoordinateInsideMazeField(Coordinate coord) {
        return coord.col() >= 0
            && coord.col() < width
            && coord.row() >= 0
            && coord.row() < height;
    }

    private boolean isMazeSizesValid(int height, int width) {
        return width > 1 && height > 1 && width <= MAX_MAZE_WIDTH && height <= MAX_MAZE_HEIGHT;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Cell getCell(Coordinate coord) {
        if (!isCoordinateInsideMazeField(coord)) {
            throwInvalidCoordinatesException();
        }
        return field[coord.row()][coord.col()];
    }

    public void changeCellType(Coordinate coord, Cell.Type newCellType) {
        if (!isCoordinateInsideMazeField(coord)) {
            throwInvalidCoordinatesException();
        }
        field[coord.row()][coord.col()] = new Cell(coord.row(), coord.col(), newCellType);
    }

    private void throwInvalidCoordinatesException() {
        throw new IllegalArgumentException("Введёные координаты лежат вне поля лабиринта!");
    }

    private void initializeMazeField() {
        field = new Cell[height][width];
        for (var y = 0; y < height; y++) {
            for (var x = 0; x < width; x++) {
                field[y][x] = new Cell(y, x, Cell.Type.WALL);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Maze maze)) {
            return false;
        } else if (maze.getWidth() != this.width
            || maze.getHeight() != this.height) {
            return false;
        }

        for (var row = 0; row < field.length; row++) {
            for (var col = 0; col < field[row].length; col++) {
                if (!field[row][col].equals(maze.getCell(new Coordinate(row, col)))) {
                    return false;
                }
            }
        }

        return true;
    }

    // Автоматически предложено IntelliJ IDEA, для прохождения checkstyl`a
    @Override
    public int hashCode() {
        int result = Objects.hash(height, width);
        result = 31 * result + Arrays.deepHashCode(field);
        return result;
    }
}
