package edu.project2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CellTest {
    @Test
    public void cellMethodsCorrectWorks() {
        var cell = new Cell(-10, 9, Cell.Type.WALL);

        Assertions.assertEquals(-10, cell.getRow());
        Assertions.assertEquals(9, cell.getCol());
        Assertions.assertEquals(Cell.Type.WALL, cell.getType());
        Assertions.assertFalse(cell.visited());

        cell.makeVisited();
        Assertions.assertTrue(cell.visited());
    }

    @Test
    public void cellEqualsCorrectWithCells() {
        var cell1 = new Cell(0, 0, Cell.Type.PASSAGE);
        var cell2 = new Cell(0, 0, Cell.Type.PASSAGE);

        Assertions.assertEquals(cell1, cell2);
    }

    @Test
    public void cellEqualsCorrectWorksWithNonCells() {
        var cell = new Cell(1, 2, Cell.Type.WALL);
        var obj = new Object();

        Assertions.assertNotEquals(cell, obj);
    }
}
