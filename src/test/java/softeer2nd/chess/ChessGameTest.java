package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.createBlank;
import static softeer2nd.chess.pieces.Piece.createWhitePawn;

@DisplayName("체스 게임 테스트")
public class ChessGameTest {
    private ChessGame chessGame;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("주어진 좌표에 있는 기물들이 주어진 좌표로 이동한다.")
    void move() throws Exception {
        // given
        chessGame.initialize();
        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");

        // when
        movePiece(sourcePosition, targetPosition);

        // then
        assertEquals(createBlank(), board.findPiece(sourcePosition));
        assertEquals(createWhitePawn(), board.findPiece(targetPosition));
    }

    private void movePiece(Position sourcePosition, Position targetPosition) {
        chessGame.move(sourcePosition, targetPosition);
    }
}