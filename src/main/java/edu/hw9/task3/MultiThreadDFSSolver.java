package edu.hw9.task3;

import edu.project2.Cell;
import edu.project2.Coordinate;
import edu.project2.Maze;
import edu.project2.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class MultiThreadDFSSolver implements Solver {
    private static final int[][] LAMBDAS_TO_STEP = new int[][] {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1}
    };
    private List<Coordinate> path;
    private volatile boolean[][] visitedCells;

    private void validateCoordinates(Maze maze, Coordinate end, boolean endCoord) {
        if (!maze.isCoordinateInsideMazeField(end)
            || maze.getCell(end).type() == Cell.Type.WALL) {
            throw new IllegalArgumentException("%s точка лабиринта должна быть в его пределах и не должна быть стеной!"
                .formatted(endCoord ? "Конечная" : "Начальная"));
        }
    }

    @Override
    public List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end) {
        if (maze == null) {
            return Collections.emptyList();
        }

        validateCoordinates(maze, start, false);
        validateCoordinates(maze, end, true);

        path = new ArrayList<>();
        visitedCells = new boolean[maze.getHeight()][maze.getWidth()];

        try (var pool = new ForkJoinPool()) {
            var task = new DFSTask(maze, start, end);
            path.addAll(pool.invoke(task));
        }

        return path;
    }

    private class DFSTask extends RecursiveTask<List<Coordinate>> {
        private final Maze maze;
        private final Coordinate current;
        private final Coordinate end;

        private DFSTask(Maze maze, Coordinate current, Coordinate end) {
            this.maze = maze;
            this.current = current;
            this.end = end;
        }

        @Override
        protected List<Coordinate> compute() {
            var localPath = new ArrayList<Coordinate>();

            if (current.equals(end)) {
                localPath.add(current);
                return localPath;
            }

            var neighbours = new ArrayList<Coordinate>();
            if (!visitedCells[current.row()][current.col()]) {
                for (var lambdas : LAMBDAS_TO_STEP) {
                    var newCoord = new Coordinate(current.row() + lambdas[0], current.col() + lambdas[1]);
                    if (maze.isCoordinateInsideMazeField(newCoord)
                        && maze.getCell(newCoord).type() != Cell.Type.WALL
                        && !visitedCells[newCoord.row()][newCoord.col()]) {
                        neighbours.add(newCoord);
                    }
                }
            }
            visitedCells[current.row()][current.col()] = true;

            var subtasks = new ArrayList<DFSTask>();
            if (!neighbours.isEmpty()) {
                for (var n : neighbours) {
                    if (!visitedCells[n.row()][n.col()]) {
                        var subtask = new DFSTask(maze, n, end);
                        subtask.fork();
                        subtasks.add(subtask);
                    }
                }
            }

            for (var subtask : subtasks) {
                var result = subtask.join();

                if (result.contains(end)) {
                    localPath.addAll(result);
                    localPath.add(current);
                    break;
                }
            }

            return localPath;
        }
    }
}
