package game;

public interface Board {
    Position getPosition();

    TwoPlayerGameState makeMove(Discrete2dMove move);
}
