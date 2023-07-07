package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.Piece;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

@DisplayName("체스판 테스트")
public class BoardTest {
    private Board board;
    static final String BLANK_RANK = appendNewLine("........");

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("기물들은 주어진 좌표에 생성된다.")
    void add() {
        String whiteRookPosition = "e1";
        String whiteKingPosition = "f1";
        String whitePawnPosition1 = "g2";
        String whitePawnPosition2 = "f3";

        board.put(Piece.createWhiteRook(), whiteRookPosition);
        board.put(Piece.createWhiteKing(), whiteKingPosition);
        board.put(Piece.createWhitePawn(), whitePawnPosition1);
        board.put(Piece.createWhitePawn(), whitePawnPosition2);

        assertEquals(Piece.createWhiteRook(), board.findPiece(whiteRookPosition));
        assertEquals(Piece.createWhiteKing(), board.findPiece(whiteKingPosition));
        assertEquals(Piece.createWhitePawn(), board.findPiece(whitePawnPosition1));
        assertEquals(Piece.createWhitePawn(), board.findPiece(whitePawnPosition2));
    }

    @Test
    @DisplayName("빈 체스판을 생성한다.")
    void createEmpty() {
        board.initializeEmpty();

        assertEquals(0, board.pieceAllCount());

        assertEquals(
                BLANK_RANK + BLANK_RANK + BLANK_RANK + BLANK_RANK +
                        BLANK_RANK + BLANK_RANK + BLANK_RANK + BLANK_RANK,
                board.showBoard());
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
                board.showBoard());
    }

    @Test
    @DisplayName("체스판의 기물과 해당하는 색의 개수를 받아야 한다.")
    void getPieceCount() {
        board.initialize();

        assertEquals(8, board.pieceCount(Piece.Type.PAWN, Piece.Color.WHITE));
        assertEquals(8, board.pieceCount(Piece.Type.PAWN, Piece.Color.BLACK));
        assertEquals(1, board.pieceCount(Piece.Type.KING, Piece.Color.WHITE));
        assertEquals(1, board.pieceCount(Piece.Type.KING, Piece.Color.BLACK));
        assertEquals(1, board.pieceCount(Piece.Type.QUEEN, Piece.Color.WHITE));
        assertEquals(1, board.pieceCount(Piece.Type.QUEEN, Piece.Color.BLACK));
        assertEquals(2, board.pieceCount(Piece.Type.ROOK, Piece.Color.WHITE));
        assertEquals(2, board.pieceCount(Piece.Type.ROOK, Piece.Color.BLACK));
        assertEquals(2, board.pieceCount(Piece.Type.BISHOP, Piece.Color.WHITE));
        assertEquals(2, board.pieceCount(Piece.Type.BISHOP, Piece.Color.BLACK));
        assertEquals(2, board.pieceCount(Piece.Type.KNIGHT, Piece.Color.WHITE));
        assertEquals(2, board.pieceCount(Piece.Type.KNIGHT, Piece.Color.BLACK));

        assertEquals(16, board.pieceCount(Piece.Color.WHITE));
        assertEquals(16, board.pieceCount(Piece.Color.BLACK));
    }

    @Test
    @DisplayName("주어진 위치에 존재하는 기물의 종류를 반환한다.")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece("a8"));
        assertEquals(Piece.createBlackRook(), board.findPiece("h8"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("a1"));
        assertEquals(Piece.createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("주어진 좌표로 임의의 기물을 추가한다.")
    public void put() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = Piece.createBlackRook();
        board.put(piece, position);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("현재 체스판에 남아있는 기물들의 점수를 계산한다.")
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("g3", Piece.createWhitePawn());
        addPiece("g4", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, board.calculatePoint(Piece.Color.BLACK), 0.01);
        assertEquals(7.5, board.calculatePoint(Piece.Color.WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    private void addPiece(String position, Piece piece) {
        board.put(piece, position);
    }
}
