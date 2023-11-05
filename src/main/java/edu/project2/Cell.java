package edu.project2;

public class Cell {
    private final int row;
    private final int col;
    private final Type type;
    private boolean visited;

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
        this.visited = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Type getType() {
        return type;
    }

    public boolean visited() {
        return visited;
    }

    public void makeVisited() {
        visited = true;
    }

    public enum Type {
        WALL,
        PASSAGE
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell cell)) {
            return false;
        }

        return cell.row == this.row
            && cell.col == this.col
            && cell.type.equals(this.type);
    }

    // Автоматически предложено IntelliJ IDEA, для прохождения checkstyl`a
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
