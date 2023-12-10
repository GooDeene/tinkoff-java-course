package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class RecursiveBacktrackerGeneratorTest {
    private Maze generateMazeSeed228Manually() {
        var maze = new Maze(3, 3);
        maze.changeCellType(new Coordinate(0, 0), Cell.Type.PASSAGE);
        maze.changeCellType(new Coordinate(0, 1), Cell.Type.PASSAGE);
        maze.changeCellType(new Coordinate(0, 2), Cell.Type.PASSAGE);
        maze.changeCellType(new Coordinate(1, 2), Cell.Type.PASSAGE);
        maze.changeCellType(new Coordinate(2, 2), Cell.Type.PASSAGE);
        maze.changeCellType(new Coordinate(2, 1), Cell.Type.PASSAGE);
        maze.changeCellType(new Coordinate(2, 0), Cell.Type.PASSAGE);

        return maze;
    }

    @Test
    public void generatorWithSeepWorksCorrectlyWithCorrectMazeSizes() {
        var generator = new RecursiveBacktrackerGenerator();
        var maze = generator.generateMaze(3, 3, 228);
        var manuallyMaze = generateMazeSeed228Manually();

        Assertions.assertEquals(maze, manuallyMaze);
    }

    @Test
    public void generateWithoutSeedWorksCorrectly() {
        var maze = new RecursiveBacktrackerGenerator().generateMaze(4, 3);

        Assertions.assertEquals(4, maze.getHeight());
        Assertions.assertEquals(3, maze.getWidth());
        Assertions.assertEquals(
            new Cell(0, 0, Cell.Type.PASSAGE),
            maze.getCell(new Coordinate(0, 0))
        );
    }

    @Test
    public void generateWorksCorrectlyWithIncorrectMazeSizes() {
        assertThatThrownBy(() -> new RecursiveBacktrackerGenerator().generateMaze(-1, 10))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Рамзер лабиринта должен быть больше 2 x 2, но меньше 700 x 700");
    }
}
