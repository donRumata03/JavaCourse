package game.players;

import game.Discrete2dMove;
import game.Player;
import game.Position;
import game.TicTacToeBoard;


public class CheatingPlayer implements Player {
    @Override
    public Discrete2dMove makeMove(Position position) {
        final TicTacToeBoard board = (TicTacToeBoard) position;
        Discrete2dMove first = null;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                final Discrete2dMove move = new Discrete2dMove(r, c, position.getNextTurn());
                if (position.isValid(move)) {
                    if (first == null) {
                        first = move;
                    } else {
                        board.makeMove(move);
                    }
                }
            }
        }
        return first;
    }
}
