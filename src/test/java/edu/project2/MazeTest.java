package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MazeTest {
    @Test
    public void mazeInitializeCorrect() {
        var maze = new Maze(5, 4);

        Assertions.assertEquals(5, maze.getHeight());
        Assertions.assertEquals(4, maze.getWidth());
        Assertions.assertEquals(new Cell(0, 0, Cell.Type.WALL), maze.getCell(new Coordinate(0, 0)));
        Assertions.assertEquals(new Cell(4, 3, Cell.Type.WALL), maze.getCell(new Coordinate(4, 3)));
    }

    @Test
    public void mazeMethodsCorrect() {
        var maze = new Maze(3, 4);
        var coord = new Coordinate(0, 0);

        Assertions.assertEquals(new Cell(0, 0, Cell.Type.WALL), maze.getCell(coord));

        maze.changeCellType(coord, Cell.Type.PASSAGE);
        Assertions.assertEquals(new Cell(0, 0, Cell.Type.PASSAGE), maze.getCell(coord));

        Assertions.assertTrue(maze.isCoordinateInsideMazeField(coord));
        Assertions.assertTrue(maze.isCoordinateInsideMazeField(new Coordinate(2, 3)));
        Assertions.assertFalse(maze.isCoordinateInsideMazeField(new Coordinate(-1, -1)));
        Assertions.assertFalse(maze.isCoordinateInsideMazeField(new Coordinate(3, 4)));
    }

    @Test
    public void mazeInitCorrectWorksWithIncorrectSizes() {
        assertThatThrownBy(() -> new Maze(-1, 1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Рамзер лабиринта должен быть больше 2 x 2, но меньше 700 x 700");

        assertThatThrownBy(() -> new Maze(10000, 10))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Рамзер лабиринта должен быть больше 2 x 2, но меньше 700 x 700");
    }

    @Test
    public void mazeMethodsCorrectWorksWithIncorrectData() {
        var maze = new Maze(5, 5);

        assertThatThrownBy(() -> maze.getCell(new Coordinate(-1, 4)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Введёные координаты лежат вне поля лабиринта!");

        assertThatThrownBy(() -> maze.changeCellType(new Coordinate(25, -8), Cell.Type.PASSAGE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Введёные координаты лежат вне поля лабиринта!");
    }

    @Test
    public void mazesEqualsCorrect() {
        var maze1 = new Maze(3, 3);
        var maze2 = new Maze(3, 3);
        var maze3 = new Maze(3, 4);
        var maze4 = new Maze(3, 3);
        maze1.changeCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);
        maze2.changeCellType(new Coordinate(1, 1), Cell.Type.PASSAGE);

        Assertions.assertEquals(maze1, maze2);
        Assertions.assertNotEquals(maze1, maze3);
        Assertions.assertNotEquals(maze1, maze4);
    }
}
