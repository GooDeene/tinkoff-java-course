package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class PrettyRendererTest {
    private static final String WALL_SYMBOL = "\033[48;5;233m   \033[0m";
    private static final String PASSAGE_SYMBOL = "\033[48;5;252m   \033[0m";
    private static final String PATH_SYMBOL = "\033[38;5;9;48;5;252m ● \033[0m";

    @Test
    public void rendererCorrectWorksWithNullMazeOrPath() {
        var render = new PrettyConsoleRenderer().render(null);
        Assertions.assertNull(render);

        var render2 = new PrettyConsoleRenderer().render(new Maze(3, 3), null);
        Assertions.assertNotNull(render2);
    }

    private String getCorrectMaze3x3Seed228RenderWithoutPath() {
        return WALL_SYMBOL.repeat(5) + '\n' +
            WALL_SYMBOL +
            PASSAGE_SYMBOL.repeat(3) + WALL_SYMBOL + '\n' +
            WALL_SYMBOL.repeat(3) + PASSAGE_SYMBOL + WALL_SYMBOL + '\n' +
            WALL_SYMBOL + PASSAGE_SYMBOL.repeat(3) + WALL_SYMBOL + '\n' +
            WALL_SYMBOL.repeat(5) + '\n';
    }

    private String getCorrectMaze3x3Seed228RenderWithPath() {
        return WALL_SYMBOL.repeat(5) + '\n' +
            WALL_SYMBOL +
            PATH_SYMBOL.repeat(3) + WALL_SYMBOL + '\n' +
            WALL_SYMBOL.repeat(3) + PATH_SYMBOL + WALL_SYMBOL + '\n' +
            WALL_SYMBOL + PASSAGE_SYMBOL.repeat(2) + PATH_SYMBOL + WALL_SYMBOL + '\n' +
            WALL_SYMBOL.repeat(5) + '\n';
    }

    @Test
    public void rendererCorrectWorksWithCorrectMazeWithoutPath() {
        var maze = new RecursiveBacktrackerGenerator().generateMaze(3, 3, 228);
        var mazeRender = new PrettyConsoleRenderer().render(maze).toCharArray();
        var correctRender = getCorrectMaze3x3Seed228RenderWithoutPath().toCharArray();

        for (var i = 0; i < mazeRender.length; i++) {
            Assertions.assertEquals(correctRender[i], mazeRender[i]);
        }
    }

    @Test public void rendererCorrectWorksWithCorrectMazeWithPath() {
        var correctPath = new ArrayList<Coordinate>();
        correctPath.add(new Coordinate(0, 0));
        correctPath.add(new Coordinate(0, 1));
        correctPath.add(new Coordinate(0, 2));
        correctPath.add(new Coordinate(1, 2));
        correctPath.add(new Coordinate(2, 2));

        var maze = new RecursiveBacktrackerGenerator().generateMaze(3, 3, 228);
        var mazeWithPathRender = new PrettyConsoleRenderer().render(maze, correctPath).toCharArray();
        var correctPathRender = getCorrectMaze3x3Seed228RenderWithPath().toCharArray();

        for (var i = 0; i < correctPathRender.length; i++) {
            Assertions.assertEquals(correctPathRender[i], mazeWithPathRender[i]);
        }
    }
}
