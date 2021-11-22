package game.players;

import game.Discrete2dMove;
import game.Player;
import game.Position;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Discrete2dMove makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getNextTurn());
        return new Discrete2dMove(in.nextInt() - 1, in.nextInt() - 1, position.getNextTurn());
    }
}
