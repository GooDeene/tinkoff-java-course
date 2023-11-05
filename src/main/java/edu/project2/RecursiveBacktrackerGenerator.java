package edu.project2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktrackerGenerator implements Generator {
    private static final int[][] LAMBDAS_TO_MAKE_STEP = new int[][]{
            {-2, 0},
            {2, 0},
            {0, -2},
            {0, 2}
    };

    @Override
    public Maze generate(int height, int width) {
        return generate(height, width, new Random().nextLong());
    }

    @Override
    public Maze generate(int height, int width, long generatorSeed) {
        var maze = new Maze(height, width);
        var randomizer = new Random(generatorSeed);
        var visitedCells = new Stack<Coordinate>();
        var currentPosition = new Coordinate(0, 0);

        maze.changeCellType(currentPosition, Cell.Type.PASSAGE);
        visitedCells.push(currentPosition);

        while (!visitedCells.isEmpty()) {
            var possibleSteps = new ArrayList<Coordinate>();
            for (var lambdas : LAMBDAS_TO_MAKE_STEP) {
                var neighbourCord = new Coordinate(
                    currentPosition.row() + lambdas[0],
                    currentPosition.col() + lambdas[1]
                );
                if (maze.isCoordinateInsideMazeField(neighbourCord)) {
                    var cell = maze.getCell(neighbourCord);
                    if (cell.getType() == Cell.Type.WALL) {
                        possibleSteps.add(neighbourCord);
                    }
                }
            }

            if (!possibleSteps.isEmpty()) {
                var nextPosition = possibleSteps.get(randomizer.nextInt(possibleSteps.size()));
                makePassage(maze, currentPosition, nextPosition);
                currentPosition = nextPosition;
                visitedCells.push(currentPosition);
            } else {
                currentPosition = visitedCells.pop();
            }
        }

        return maze;
    }

    private void makePassage(Maze maze, Coordinate from, Coordinate to) {
        for (var col = Math.min(from.col(), to.col()); col <= Math.max(from.col(), to.col()); col++) {
            for (var row = Math.min(from.row(), to.row()); row <= Math.max(from.row(), to.row()); row++) {
                var cordToDig = new Coordinate(row, col);
                if (!from.equals(cordToDig)) {
                    maze.changeCellType(cordToDig, Cell.Type.PASSAGE);
                }
            }
        }
    }
}
