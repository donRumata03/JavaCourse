package game;

import game.players.CheatingPlayer;
import game.players.HumanPlayer;
import game.players.RandomPlayer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final TwoPlayerGameState result = new TwoPlayerGame(
                new MNKBoard(3, 3, 3),
                new RandomPlayer(),
//                new CheatingPlayer()
                new HumanPlayer(new Scanner(System.in))
        ).play(true);
        switch (result) {
            case FIRST_WINS -> System.out.println("First player won");
            case SECOND_WINS -> System.out.println("Second player won");
            case DRAW -> System.out.println("Draw");
            default -> throw new AssertionError("TwoPlayerGame reported about incorrect result => its work is incorrect!");
        }
    }
}
