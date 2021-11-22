package game;

public interface Position {
    CellState getNextTurn();

    boolean isValid(Discrete2dMove move);

    CellState getCell(int row, int column);
}
