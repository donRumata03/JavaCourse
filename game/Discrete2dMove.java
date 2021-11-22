package game;

public class Discrete2dMove {
    private final int row;
    private final int col;
    private final CellState value;

    public Discrete2dMove(int row, int col, CellState value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public CellState getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Move(%s, %d, %d)", value, row + 1, col + 1);
    }
}
