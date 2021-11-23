package game;

public class Discrete2dMove {
    private final Position2d position;
    private final CellState value;

    public Discrete2dMove(Position2d position, CellState value) {
        this.position = position;
        this.value = value;
    }


    public CellState getValue() {
        return value;
    }

    public Position2d getPosition() {
        return position;
    }
}
