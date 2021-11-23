package game;

import java.util.Arrays;

public class MNKBoard implements Board {
    private final int rows;
    private final int cols;
    private final int k;

    private final CellState[][] field;
    private int cellsFilled = 0;

    public MNKBoard(int rows, int cols, int k) {
        this.rows = rows;
        this.cols = cols;
        this.k = k;

        this.field = new CellState[rows][cols];
        for (CellState[] row : field) {
            Arrays.fill(row, CellState.E);
        }

    }

    @Override
    public TwoPlayerGameState makeMove(Discrete2dMove move) {
        return null;
    }

    @Override
    public UnmodifiableBoardView getUnmodifiableView() {
        return null;
    }


    enum TurnResult {
        GAME_NOT_FINISHED,
        WIN,
        DRAW
    }

    private TurnResult checkGameStateUpdate(Position2d updatedPosition) {
        cellsFilled++;

        // Updates will contain updatedPosition and in case of win it would contain current value:

        int distX = countSameTowards(updatedPosition, 0, 1) + 1 + countSameTowards(updatedPosition, 0, -1);
        int distY = countSameTowards(updatedPosition, 1, 0) + 1 + countSameTowards(updatedPosition, -1, 0);

        int distSlash = countSameTowards(updatedPosition, 1, -1) + 1 + countSameTowards(updatedPosition, -1, 1);
        int distBackslash = countSameTowards(updatedPosition, 1, 1) + 1 + countSameTowards(updatedPosition, -1, -1);

        if (distX >= k || distY >= k || distSlash >= k || distBackslash >= k) {
            return TurnResult.WIN;
        } else if (cellsFilled == rows * cols) {
            return TurnResult.DRAW;
        } else {
            return TurnResult.GAME_NOT_FINISHED;
        }
    }

    private int countSameTowards(Position2d startPosition, int incrementY, int incrementX) {
        int x = startPosition.col;
        int y = startPosition.row;

        int sameFoundInclusive = 0;
        for (; sameFoundInclusive <= k; sameFoundInclusive++) {
            y += incrementY;
            x += incrementX;
            if (y < 0 || y >= rows || x < 0 || x >= cols) {
                break;
            }
        }

        return sameFoundInclusive - 1;
    }
}
