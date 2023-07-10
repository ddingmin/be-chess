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
import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.pieces.Piece.Type.BISHOP;
import static softeer2nd.chess.pieces.Piece.createWhiteKing;

@DisplayName("체스 게임 테스트")
class ChessGameTest {
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

    @Test
    @DisplayName("현재 체스판에 남아있는 기물들의 점수를 계산한다.")
    void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece(new Position("b6"), createBlackPawn());
        addPiece(new Position("e6"), createBlackQueen());
        addPiece(new Position("b8"), createBlackKing());
        addPiece(new Position("c8"), createBlackRook());

        addPiece(new Position("f2"), createWhitePawn());
        addPiece(new Position("g2"), createWhitePawn());
        addPiece(new Position("g3"), createWhitePawn());
        addPiece(new Position("g4"), createWhitePawn());
        addPiece(new Position("e1"), createWhiteRook());
        addPiece(new Position("f1"), createWhiteKing());

        assertEquals(15.0, chessGame.calculatePoint(BLACK), 0.01);
        assertEquals(7.5, chessGame.calculatePoint(WHITE), 0.01);
    }

    @Test
    @DisplayName("기물별로 점수를 정렬한다.")
    void sortByPiecePoint() {
        // when
        board.initializeEmpty();

        addPiece(new Position("b1"), createWhitePawn());
        addPiece(new Position("b2"), createWhitePawn());
        addPiece(new Position("b3"), createWhitePawn());
        addPiece(new Position("b4"), createWhitePawn());
        addPiece(new Position("c5"), createWhiteKnight());

        addPiece(new Position("b6"), createBlackPawn());
        addPiece(new Position("e6"), createBlackQueen());
        addPiece(new Position("b8"), createBlackKing());
        addPiece(new Position("c8"), createBlackRook());

        // then
        assertEquals(Arrays.asList(BISHOP, KING, QUEEN, ROOK, PAWN, KNIGHT), chessGame.sortAscByPiecePoint(WHITE));
        assertEquals(Arrays.asList(QUEEN, ROOK, PAWN, KNIGHT, KING, BISHOP), chessGame.sortDescByPiecePoint(BLACK));
    }

    private void addPiece(Position position, Piece piece) {
        board.put(piece, position);
    }

    private void movePiece(Position sourcePosition, Position targetPosition) {
        chessGame.move(sourcePosition, targetPosition);
    }
}