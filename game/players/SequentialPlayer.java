package game.players;

import game.Discrete2dMove;
import game.Player;
import game.Position;

public class SequentialPlayer implements Player {
    @Override
    public Discrete2dMove makeMove(Position position) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final Discrete2dMove move = new Discrete2dMove(r, c, position.getNextTurn());
                if (position.isValid(move)) {
                    return move;
                }
            }
        }
        throw new AssertionError("No valid moves");
    }
}
