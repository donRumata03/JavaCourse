package game;

public interface UnmodifiableBoardView {
    int getNextTurnIndex();
    default int getNextPlayerIndex() {
        return getNextTurnIndex() % 2;
    }

    boolean isValid(Discrete2dMove move);

    CellState cellStateAt(Position2d position);
}
