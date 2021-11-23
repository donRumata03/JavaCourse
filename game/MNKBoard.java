package game;

import java.util.Arrays;
import java.util.stream.Collectors;

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
        if (!this.isValid(move)) {
            return TwoPlayerGameState.
        }
    }

    @Override
    public UnmodifiableBoardView getUnmodifiableView() {
        return null;
    }


    private boolean isValid(Discrete2dMove move) {
        Position2d pos = move.getPosition();
        return
            0 <= pos.col && pos.col < cols &&
            0 <= pos.row && pos.row < rows &&
                field[pos.row][pos.col] == CellState.E;
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


    @Override
    public String toString() {
        // Determine max number width:
        int maxWidth = Integer.max(Integer.toString(rows).length(), Integer.toString(cols).length());

        StringBuilder builder = new StringBuilder();

        String formattingString = ("%0" + (maxWidth + 1) + "d").repeat(cols);
        for (int y = 0; y < rows; y++) {
            builder.append(String.format(formattingString,
                Arrays.stream(field[y]).map(CellState::toString).toArray()
                ));
        }

        return builder.toString();
    }
}
