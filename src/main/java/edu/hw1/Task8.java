package edu.hw1;

public class Task8 {
    private Task8() {
    }

    private static final int FIELD_SIZE_LEN = 8;
    private static final int[] POSSIBLE_NEIGHBOR_LAMBDAS = new int[]{-2, -1, 1, 2};

    private static boolean isKnightInPossibleNeighbors(int x, int y, int[][] board) {
        for (int i : POSSIBLE_NEIGHBOR_LAMBDAS) {
            for (int j : POSSIBLE_NEIGHBOR_LAMBDAS) {
                if (Math.abs(i) != Math.abs(j)
                    && isPointValid(x + i, y + j)
                    && board[x + i][y + j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isPointValid(int x, int y) {
        return x >= 0 && x < FIELD_SIZE_LEN && y >= 0 && y < FIELD_SIZE_LEN;
    }

    public static boolean knightBoardCapture(int[][] board) {
        for (int i = 0; i < FIELD_SIZE_LEN; i++) {
            for (int j = 0; j < FIELD_SIZE_LEN; j++) {
                if (board[i][j] == 1) {
                    var haveNeighborKnight = isKnightInPossibleNeighbors(i, j, board);
                    if (haveNeighborKnight) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
