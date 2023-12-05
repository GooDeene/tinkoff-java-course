package edu.project2;

import java.util.List;

public class PrettyConsoleRenderer implements Renderer {
    private static final String WALL_SYMBOL = "\033[48;5;233m   \033[0m";
    private static final String PASSAGE_SYMBOL = "\033[48;5;252m   \033[0m";
    private static final String PATH_SYMBOL = "\033[38;5;9;48;5;252m ● \033[0m";

    @Override
    public String render(Maze maze) {
        return renderField(maze, null);
    }

    @Override
    public String render(Maze maze, List<Coordinate> path) {
        return renderField(maze, path);
    }

    private String renderField(Maze maze, List<Coordinate> path) {
        if (maze == null) {
            return null;
        }

        var usePath = path != null;
        var result = new StringBuilder();
        result.append(WALL_SYMBOL.repeat(maze.getWidth() + 2)).append('\n');
        for (var row = 0; row < maze.getHeight(); row++) {
            result.append(WALL_SYMBOL);
            for (var col = 0; col < maze.getWidth(); col++) {
                if (maze.getCell(new Coordinate(row, col)).type() == Cell.Type.WALL) {
                    result.append(WALL_SYMBOL);
                } else if (usePath && path.contains(new Coordinate(row, col))) {
                    result.append(PATH_SYMBOL);
                } else {
                    result.append(PASSAGE_SYMBOL);
                }
            }
            result.append(WALL_SYMBOL).append('\n');
        }

        result.append(WALL_SYMBOL.repeat(maze.getWidth() + 2)).append('\n');

        return result.toString();
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void renderThenPrintToSystemConsole(Maze maze, List<Coordinate> path) {
        System.out.println(render(maze, path));
    }

    @SuppressWarnings("RegexpSinglelineJava")
    public void renderThenPrintToSystemConsole(Maze maze) {
        System.out.println(render(maze));
    }
}
