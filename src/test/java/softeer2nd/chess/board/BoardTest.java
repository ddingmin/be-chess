package softeer2nd.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

@DisplayName("체스판 테스트")
public class BoardTest {
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

        board.put(createWhiteRook(), whiteRookPosition);
        board.put(createWhiteKing(), whiteKingPosition);
        board.put(createWhitePawn(), whitePawnPosition1);
        board.put(createWhitePawn(), whitePawnPosition2);

        assertEquals(createWhiteRook(), board.findPiece(whiteRookPosition));
        assertEquals(createWhiteKing(), board.findPiece(whiteKingPosition));
        assertEquals(createWhitePawn(), board.findPiece(whitePawnPosition1));
        assertEquals(createWhitePawn(), board.findPiece(whitePawnPosition2));
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
    public void create() throws Exception {
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

        assertEquals(1, board.pieceCount(createWhiteKing()));
        assertEquals(1, board.pieceCount(createBlackKing()));
        assertEquals(1, board.pieceCount(createWhiteQueen()));
        assertEquals(1, board.pieceCount(createBlackQueen()));
        assertEquals(2, board.pieceCount(createWhiteBishop()));
        assertEquals(2, board.pieceCount(createBlackBishop()));
        assertEquals(2, board.pieceCount(createWhiteRook()));
        assertEquals(2, board.pieceCount(createBlackRook()));
        assertEquals(2, board.pieceCount(createWhiteKnight()));
        assertEquals(2, board.pieceCount(createBlackKnight()));
        assertEquals(8, board.pieceCount(createWhitePawn()));
        assertEquals(8, board.pieceCount(createBlackPawn()));

        assertEquals(16, board.pieceCount(WHITE));
        assertEquals(16, board.pieceCount(BLACK));
    }

    @Test
    @DisplayName("주어진 위치에 존재하는 기물의 종류를 반환한다.")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(createBlackRook(), board.findPiece(new Position("a8")));
        assertEquals(createBlackRook(), board.findPiece(new Position("h8")));
        assertEquals(createWhiteRook(), board.findPiece(new Position("a1")));
        assertEquals(createWhiteRook(), board.findPiece(new Position("h1")));
    }

    @Test
    @DisplayName("주어진 좌표로 임의의 기물을 추가한다.")
    public void put() throws Exception {
        board.initializeEmpty();

        Position position = new Position("b5");
        Piece piece = createBlackRook();
        board.put(piece, position);

        assertEquals(piece, board.findPiece(position));
    }

    @Test
    @DisplayName("현재 체스판에 남아있는 기물들의 점수를 계산한다.")
    public void calculatePoint() throws Exception {
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

        assertEquals(15.0, board.calculatePoint(BLACK), 0.01);
        assertEquals(7.5, board.calculatePoint(WHITE), 0.01);
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
        assertEquals(Arrays.asList(BISHOP, KING, QUEEN, ROOK, PAWN, KNIGHT), board.sortAscByPiecePoint(WHITE));
        assertEquals(Arrays.asList(QUEEN, ROOK, PAWN, KNIGHT, KING, BISHOP), board.sortDescByPiecePoint(BLACK));
    }

    private void addPiece(Position position, Piece piece) {
        board.put(piece, position);
    }
}
