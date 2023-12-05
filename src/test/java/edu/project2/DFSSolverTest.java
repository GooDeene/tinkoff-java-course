package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class DFSSolverTest {
    @Test
    public void solverFindSolutionIntoCorrectMaze() {
        var maze = new RecursiveBacktrackerGenerator().generateMaze(3, 3, 228);
        var correctPath = new ArrayList<Coordinate>();
        correctPath.add(new Coordinate(0, 0));
        correctPath.add(new Coordinate(0, 1));
        correctPath.add(new Coordinate(0, 2));
        correctPath.add(new Coordinate(1, 2));
        correctPath.add(new Coordinate(2, 2));

        var path = new DFSSolver().solve(
            maze,
            new Coordinate(0, 0),
            new Coordinate(2, 2)
        );

        Assertions.assertEquals(correctPath, path);
        Assertions.assertEquals(correctPath.size(), path.size());
    }

    @Test
    public void solverFindSolutionIntoMazeWithForks() {
        var maze = new RecursiveBacktrackerGenerator().generateMaze(5, 5, 228);
        var correctPath = new ArrayList<Coordinate>();
        var correctPathCoords = new int[][] {
            {0, 0}, {0, 1}, {0, 2},
            {1, 2}, {2, 2}, {3, 2},
            {4, 2}, {4, 3}, {4, 4},
        };
        for (var c : correctPathCoords) {
            correctPath.add(new Coordinate(c[0], c[1]));
        }

        var path = new DFSSolver().solve(
            maze,
            new Coordinate(0, 0),
            new Coordinate(4, 4)
        );

        Assertions.assertEquals(correctPath, path);
        Assertions.assertEquals(correctPath.size(), path.size());
    }

    @Test
    public void solverWorksCorrectWithIncorrectCoordinates() {
        var maze = new RecursiveBacktrackerGenerator().generateMaze(4, 4);
        assertThatThrownBy(() -> new DFSSolver().solve(
            maze,
            new Coordinate(0, 0),
            new Coordinate(3, 3)
        ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Конечная точка лабиринта должна быть в его пределах и не должна быть стеной!");

        assertThatThrownBy(() -> new DFSSolver().solve(
            maze,
            new Coordinate(-1, 0),
            new Coordinate(0, 0)
        ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Начальная точка лабиринта должна быть в его пределах и не должна быть стеной!");
    }

    @Test
    public void solverCorrectWorksWithNullMaze() {
        var path = new DFSSolver().solve(
            null,
            new Coordinate(0, 0),
            new Coordinate(100, 100)
        );

        Assertions.assertTrue(path.isEmpty());
    }
}
