package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;
import softeer2nd.chess.pieces.Piece;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.pieces.Color.*;
import static softeer2nd.chess.pieces.Type.*;

@DisplayName("체스 게임 테스트")
class ChessGameTest {
    private ChessGame chessGame;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
        chessGame.initialize();
    }

    @Test
    @DisplayName("주어진 좌표에 있는 기물들이 주어진 좌표로 이동한다.")
    void move() throws Exception {
        // given
        Position sourcePosition = new Position("b2");
        Position targetPosition = new Position("b3");

        // when
        movePiece(sourcePosition, targetPosition);

        // then
        assertEquals(createBlank(), board.findPiece(sourcePosition));
        assertEquals(Piece.create(PAWN, WHITE), board.findPiece(targetPosition));
    }

    @Test
    @DisplayName("현재 체스판에 남아있는 기물들의 점수를 계산한다.")
    void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece(new Position("b6"), create(PAWN, BLACK));
        addPiece(new Position("e6"), create(QUEEN, BLACK));
        addPiece(new Position("b8"), create(KING, BLACK));
        addPiece(new Position("c8"), create(ROOK, BLACK));

        addPiece(new Position("f2"), create(PAWN, WHITE));
        addPiece(new Position("g2"), create(PAWN, WHITE));
        addPiece(new Position("g3"), create(PAWN, WHITE));
        addPiece(new Position("g4"), create(PAWN, WHITE));
        addPiece(new Position("e1"), create(ROOK, WHITE));
        addPiece(new Position("f1"), create(KING, WHITE));

        assertEquals(15.0, chessGame.calculatePoint(BLACK), 0.01);
        assertEquals(7.5, chessGame.calculatePoint(WHITE), 0.01);
    }

    @Test
    @DisplayName("기물별로 점수를 정렬한다.")
    void sortByPiecePoint() {
        // when
        board.initializeEmpty();

        addPiece(new Position("b1"), create(PAWN, WHITE));
        addPiece(new Position("b2"), create(PAWN, WHITE));
        addPiece(new Position("b3"), create(PAWN, WHITE));
        addPiece(new Position("b4"), create(PAWN, WHITE));
        addPiece(new Position("c5"), create(KNIGHT, WHITE));

        addPiece(new Position("b6"), create(PAWN, BLACK));
        addPiece(new Position("e6"), create(QUEEN, BLACK));
        addPiece(new Position("b8"), create(KING, BLACK));
        addPiece(new Position("c8"), create(ROOK, BLACK));

        // then
        assertEquals(Arrays.asList(BISHOP, KING, QUEEN, ROOK, PAWN, KNIGHT), chessGame.sortAscByPiecePoint(WHITE));
        assertEquals(Arrays.asList(QUEEN, ROOK, PAWN, KNIGHT, KING, BISHOP), chessGame.sortDescByPiecePoint(BLACK));
    }

    @Test
    @DisplayName("이동할 위치에 다른 색 기물이 존재하면 이동할 수 있다.")
    void movePositionExistDiffColor() {
        addPiece(new Position("e7"), create(KING, WHITE));
        assertDoesNotThrow(() -> movePiece(new Position("e7"), new Position("e8")));
        assertDoesNotThrow(() -> movePiece(new Position("e8"), new Position("d8")));
        assertDoesNotThrow(() -> movePiece(new Position("d8"), new Position("c8")));
        assertDoesNotThrow(() -> movePiece(new Position("c8"), new Position("c7")));
    }

    @Test
    @DisplayName("지속적인 이동을 하는 기물들의 경로에 기물이 존재하면 에러가 발생한다.")
    void moveMovingPieceOnExistRoute() {
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("a1"), new Position("a6")));
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("a1"), new Position("a7")));
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("a1"), new Position("a8")));

        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("d8"), new Position("a8")));
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("d8"), new Position("e8")));
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("d8"), new Position("c7")));
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("d8"), new Position("h4")));


        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("f1"), new Position("g2")));
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("f1"), new Position("a6")));
        assertThrows(IllegalArgumentException.class,() -> movePiece(new Position("f1"), new Position("e6")));
    }

    private void addPiece(Position position, Piece piece) {
        board.put(piece, position);
    }

    private void movePiece(Position sourcePosition, Position targetPosition) {
        chessGame.move(sourcePosition, targetPosition);
    }
}
