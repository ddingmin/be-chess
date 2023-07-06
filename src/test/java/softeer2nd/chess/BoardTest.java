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

    @BeforeEach
    public void setup() {
        board = new Board();
    }

    @Test
    @DisplayName("기물들은 주어진 좌표에 생성된다.")
    void add() {
        String point = "a3";

        board.put(Piece.createBlackKing(), point);

        assertEquals(1, board.pieceCount());
        assertEquals(1, board.pieceCount(Piece.Type.KING, Piece.Color.BLACK));
    }

    @Test
    @DisplayName("체스판의 초기 기물 수는 32개, 각 위치의 올바르게 초기화 되어야 한다.")
    public void create() throws Exception {
        board.initialize();

        assertEquals(32, board.pieceCount());

        String blankRank = appendNewLine("........");

        assertEquals(
                appendNewLine("RNBQKBNR") +
                        appendNewLine("PPPPPPPP") +
                        blankRank + blankRank + blankRank + blankRank +
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
}
