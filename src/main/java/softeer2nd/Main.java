package softeer2nd;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.board.Board;

public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        ChessGame gameController = new ChessGame(board);
        gameController.main();
    }
}