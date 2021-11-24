package game.players;

import game.Discrete2dMove;
import game.Player;
import game.UnmodifiableBoardView;
import java.security.AccessControlException;


public class CheatingPlayer implements Player {
    @Override
    public Discrete2dMove makeMove(UnmodifiableBoardView view) {
        throw new DirtyUyghurException();
    }
}
