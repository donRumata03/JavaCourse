package game.players;

import game.CellState;
import game.Discrete2dMove;
import game.Player;
import game.Position2d;
import game.UnmodifiableBoardView;
import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    @Override
    public Discrete2dMove makeMove(UnmodifiableBoardView view) {
        while (true) {
            final Position2d position = new Position2d(
                    random.nextInt(3),
                    random.nextInt(3)
            );
            if (view.cellStateAt(position) == CellState.E) {
                return new Discrete2dMove(position);
            }
        }
    }
}
