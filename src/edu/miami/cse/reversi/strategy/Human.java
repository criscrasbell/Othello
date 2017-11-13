package edu.miami.cse.reversi.strategy;

import java.util.*;

import edu.miami.cse.reversi.Player;
import edu.miami.cse.reversi.Board;
import edu.miami.cse.reversi.Square;
import edu.miami.cse.reversi.Strategy;

public class Human implements Strategy {

    @Override
    public Square chooseSquare(Board board) {
        return chooseOne(board.getCurrentPossibleSquares(), board);
    }

    public static boolean isEmpty(Board board, int row, int col) {
        return board.getSquareOwners().containsKey(new Square(row, col));
    }

    public static int countFlips(Board board, Player current, Square move) {
        int flips = 0;

        Iterator it = board.getSquareOwners().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();

            int row = ((Square) pair.getKey()).getRow();
            int col = ((Square) pair.getKey()).getColumn();

            double slope = (double) (move.getColumn() - col) / (move.getRow() - row);

            if (!move.equals(pair.getKey()) && !pair.getValue().equals(current) && (Double.isInfinite(slope) || slope == 0 || Math.abs(slope) == 1)) {
                System.out.println(pair.getKey() + ", " + pair.getValue());
            }
        }

        return flips;
    }

    public static int heuristic(Board board, Player current, Square move) {
        return countFlips(board, current, move);
    }

    public static <T> T chooseOne(Set<T> itemSet, Board board) {
        List<T> itemList = new ArrayList<>(itemSet);
        int picked = 0;

        int value = heuristic(board, board.getCurrentPlayer(), (Square) itemList.get(0));

        Scanner in = new Scanner(System.in);

        int i = 0;
        for (T item : itemList) {
            System.out.println(i + " " + item);
            i++;
        }

        picked = in.nextInt();

        return itemList.get(picked);
    }
}
