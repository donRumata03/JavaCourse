package game.players;

import game.Discrete2dMove;
import game.Player;
import game.Position;
import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Discrete2dMove makeMove(Position position) {
        while (true) {
            final Discrete2dMove move = new Discrete2dMove(
                    random.nextInt(3),
                    random.nextInt(3),
                    position.getNextTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
