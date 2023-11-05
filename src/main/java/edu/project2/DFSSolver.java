package edu.project2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFSSolver implements Solver {
    private static final int[][] LAMBDAS_TO_STEP = new int[][] {
        {-1, 0},
        {1, 0},
        {0, 1},
        {0, -1}
    };

    private void validateCoordinates(Maze maze, Coordinate end, boolean endCoord) {
        if (!maze.isCoordinateInsideMazeField(end)
            || maze.getCell(end).getType() == Cell.Type.WALL) {
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

        var path = new ArrayList<Coordinate>();
        var toVisit = new Stack<Coordinate>();
        toVisit.push(start);

        while (!toVisit.isEmpty()) {
            var currentCoord = toVisit.peek();
            if (currentCoord.equals(end)) {
                path.add(currentCoord);
                break;
            }
            var neighbours = new ArrayList<Coordinate>();
            if (!maze.getCell(currentCoord).visited()) {
                for (var lambdas : LAMBDAS_TO_STEP) {
                    var newCoord = new Coordinate(currentCoord.row() + lambdas[0], currentCoord.col() + lambdas[1]);
                    if (maze.isCoordinateInsideMazeField(newCoord)
                        && maze.getCell(newCoord).getType() != Cell.Type.WALL
                        && !maze.getCell(newCoord).visited()) {
                        neighbours.add(newCoord);
                    }
                }
            }

            maze.getCell(currentCoord).makeVisited();
            if (!neighbours.isEmpty()) {
                for (var n : neighbours) {
                    toVisit.push(n);
                    path.add(currentCoord);
                }
            } else {
                path.remove(path.size() - 1);
                toVisit.pop();
            }
        }
        return path;
    }
}
