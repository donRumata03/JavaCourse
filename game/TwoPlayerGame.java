package game;

public class TwoPlayerGame {
    private final Board board;
    private final Player player1;
    private final Player player2;

    public TwoPlayerGame(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
    }

    Player getPlayerAtIndex(int index) {
        assert index <= 1;

        return index == 0 ? player1 : player2;
    }


    public TwoPlayerGameState play(boolean log) {
        while (true) {
            for (int playerIndex = 0; playerIndex < 2; playerIndex++) {
                final TwoPlayerGameState result = makeMove(getPlayerAtIndex(playerIndex), playerIndex, log);
                if (result != TwoPlayerGameState.GAME_NOT_FINISHED)  {
                    return result;
                }
            }
        }
    }

    private TwoPlayerGameState makeMove(Player player, int no, boolean log) {
        final Discrete2dMove move = player.makeMove(board.getUnmodifiableView());
        final TwoPlayerGameState result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }

        return result;
    }
}
