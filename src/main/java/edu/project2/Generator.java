package edu.project2;

public interface Generator {
    Maze generateMaze(int height, int width);

    Maze generateMaze(int height, int width, long mazeSeed);
}
