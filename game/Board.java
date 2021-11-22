package game;

public interface Board {
    TwoPlayerGameState makeMove(Discrete2dMove move);
}
