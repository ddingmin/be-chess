package softeer2nd.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;
import softeer2nd.chess.position.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Color.*;
import static softeer2nd.chess.pieces.Type.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

@DisplayName("체스판 테스트")
class BoardTest {
    private Board board;
    static final String BLANK_RANK = appendNewLine("........");

    @BeforeEach
    void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("기물들은 주어진 좌표에 생성된다.")
    void add() {
        Position whiteRookPosition = new Position("e1");
        Position whiteKingPosition = new Position("f1");
        Position whitePawnPosition1 = new Position("g2");
        Position whitePawnPosition2 = new Position("f3");

        board.put(Piece.create(Type.ROOK, WHITE), whiteRookPosition);
        board.put(Piece.create(Type.KING, WHITE), whiteKingPosition);
        board.put(Piece.create(Type.PAWN, WHITE), whitePawnPosition1);
        board.put(Piece.create(Type.PAWN, WHITE), whitePawnPosition2);

        assertEquals(Piece.create(Type.ROOK, WHITE), board.findPiece(whiteRookPosition));
        assertEquals(Piece.create(Type.KING, WHITE), board.findPiece(whiteKingPosition));
        assertEquals(Piece.create(Type.PAWN, WHITE), board.findPiece(whitePawnPosition1));
        assertEquals(Piece.create(Type.PAWN, WHITE), board.findPiece(whitePawnPosition2));
    }

    @Test
    @DisplayName("빈 체스판을 생성한다.")
    void createEmpty() {
        board.initializeEmpty();

        assertEquals(0, board.pieceAllCount());

        assertEquals(
                BLANK_RANK + BLANK_RANK + BLANK_RANK + BLANK_RANK +
                        BLANK_RANK + BLANK_RANK + BLANK_RANK + BLANK_RANK,
                board.getBoard());
    }

    @Test
    @DisplayName("체스판의 초기 기물 수는 32개, 각 위치의 올바르게 초기화 되어야 한다.")
    void create() throws Exception {
        board.initialize();

        assertEquals(32, board.pieceAllCount());

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        BLANK_RANK + BLANK_RANK + BLANK_RANK + BLANK_RANK +
                        appendNewLine("pppppppp") +
                        appendNewLine("rnbqkbnr"),
                board.getBoard());
    }

    @Test
    @DisplayName("체스판의 기물과 해당하는 색의 개수를 받아야 한다.")
    void getPieceCount() {
        board.initialize();

        assertEquals(1, board.pieceCount(Piece.create(KING, WHITE)));
        assertEquals(1, board.pieceCount(Piece.create(KING, BLACK)));
        assertEquals(1, board.pieceCount(Piece.create(QUEEN, WHITE)));
        assertEquals(1, board.pieceCount(Piece.create(QUEEN, BLACK)));
        assertEquals(2, board.pieceCount(Piece.create(BISHOP, WHITE)));
        assertEquals(2, board.pieceCount(Piece.create(BISHOP, BLACK)));
        assertEquals(2, board.pieceCount(Piece.create(ROOK, WHITE)));
        assertEquals(2, board.pieceCount(Piece.create(ROOK, BLACK)));
        assertEquals(2, board.pieceCount(Piece.create(KNIGHT, WHITE)));
        assertEquals(2, board.pieceCount(Piece.create(KNIGHT, BLACK)));
        assertEquals(8, board.pieceCount(Piece.create(PAWN, WHITE)));
        assertEquals(8, board.pieceCount(Piece.create(PAWN, BLACK)));

        assertEquals(16, board.pieceCount(WHITE));
        assertEquals(16, board.pieceCount(BLACK));
    }

    @Test
    @DisplayName("주어진 위치에 존재하는 기물의 종류를 반환한다.")
    void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.create(ROOK, BLACK), board.findPiece(new Position("a8")));
        assertEquals(Piece.create(ROOK, BLACK), board.findPiece(new Position("h8")));
        assertEquals(Piece.create(ROOK, WHITE), board.findPiece(new Position("a1")));
        assertEquals(Piece.create(ROOK, WHITE), board.findPiece(new Position("h1")));
    }

    @Test
    @DisplayName("주어진 좌표로 임의의 기물을 추가한다.")
    void put() throws Exception {
        board.initializeEmpty();

        Position position = new Position("b5");
        Piece piece = Piece.create(ROOK, BLACK);
        board.put(piece, position);

        assertEquals(piece, board.findPiece(position));
    }
}
