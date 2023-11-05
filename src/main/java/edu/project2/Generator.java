package edu.project2;

public interface Generator {
    Maze generate(int height, int width);

    Maze generate(int height, int width, long mazeSeed);
}
