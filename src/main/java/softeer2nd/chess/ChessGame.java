package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;
import softeer2nd.chess.pieces.Piece;

public class ChessGame {
    private Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void initialize() {
        board.initialize();
    }

    public void move(Position sourcePosition, Position targetPosition) {
        board.put(board.findPiece(sourcePosition), targetPosition);
        board.put(Piece.createBlank(), sourcePosition);
    }
}
