package edu.hw9;

import edu.hw9.task3.MultiThreadDFSSolver;
import edu.project2.Coordinate;
import edu.project2.RecursiveBacktrackerGenerator;
import java.util.ArrayList;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class MultiThreadDFSolverTest {
    @Test
    public void multiThreadSolverWorksCorrectly() {
        var maze = new RecursiveBacktrackerGenerator().generateMaze(3, 3, 228);

        var path = new MultiThreadDFSSolver().solve(
            maze,
            new Coordinate(0, 0),
            new Coordinate(2, 2)
        );

        Assertions.assertThat(path).containsExactlyInAnyOrder(
            new Coordinate(0, 0),
            new Coordinate(0, 1),
            new Coordinate(0, 2),
            new Coordinate(1, 2),
            new Coordinate(2, 2)
        );
    }

    @Test
    public void solverWorksCorrectWithIncorrectCoordinates() {
        var maze = new RecursiveBacktrackerGenerator().generateMaze(4, 4);
        assertThatThrownBy(() -> new MultiThreadDFSSolver().solve(
            maze,
            new Coordinate(0, 0),
            new Coordinate(3, 3)
        ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Конечная точка лабиринта должна быть в его пределах и не должна быть стеной!");

        assertThatThrownBy(() -> new MultiThreadDFSSolver().solve(
            maze,
            new Coordinate(-1, 0),
            new Coordinate(0, 0)
        ))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("Начальная точка лабиринта должна быть в его пределах и не должна быть стеной!");
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

        var path = new MultiThreadDFSSolver().solve(
            maze,
            new Coordinate(0, 0),
            new Coordinate(4, 4)
        );

        Assertions.assertThat(path).containsExactlyInAnyOrderElementsOf(correctPath);
    }

    @Test
    public void solverCorrectWorksWithNullMaze() {
        var path = new MultiThreadDFSSolver().solve(
            null,
            new Coordinate(0, 0),
            new Coordinate(100, 100)
        );

        Assertions.assertThat(path).isEmpty();
    }
}
