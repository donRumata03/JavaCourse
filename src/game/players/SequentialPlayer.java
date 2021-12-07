package game.players;

import game.CellState;
import game.Discrete2dMove;
import game.Player;
import game.Position2d;
import game.UnmodifiableBoardView;

public class SequentialPlayer implements Player {
    @Override
    public Discrete2dMove makeMove(UnmodifiableBoardView view) {
        for (int r = 0; r < view.getRows(); r++) {
            for (int c = 0; c < view.getCols(); c++) {
                Position2d position = new Position2d(r, c);
                if (view.cellStateAt(position) == CellState.E) {
                    return new Discrete2dMove(position);
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
