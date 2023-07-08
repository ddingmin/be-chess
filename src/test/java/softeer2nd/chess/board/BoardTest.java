package softeer2nd.chess.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;
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

        assertEquals(8, board.pieceCount(PAWN, WHITE));
        assertEquals(8, board.pieceCount(PAWN, BLACK));
        assertEquals(1, board.pieceCount(KING, WHITE));
        assertEquals(1, board.pieceCount(KING, BLACK));
        assertEquals(1, board.pieceCount(QUEEN, WHITE));
        assertEquals(1, board.pieceCount(QUEEN, BLACK));
        assertEquals(2, board.pieceCount(ROOK, WHITE));
        assertEquals(2, board.pieceCount(ROOK, BLACK));
        assertEquals(2, board.pieceCount(BISHOP, WHITE));
        assertEquals(2, board.pieceCount(BISHOP, BLACK));
        assertEquals(2, board.pieceCount(KNIGHT, WHITE));
        assertEquals(2, board.pieceCount(KNIGHT, BLACK));

        assertEquals(16, board.pieceCount(WHITE));
        assertEquals(16, board.pieceCount(BLACK));
    }

    @Test
    @DisplayName("주어진 위치에 존재하는 기물의 종류를 반환한다.")
    public void findPiece() throws Exception {
        board.initialize();

        assertEquals(createBlackRook(), board.findPiece("a8"));
        assertEquals(createBlackRook(), board.findPiece("h8"));
        assertEquals(createWhiteRook(), board.findPiece("a1"));
        assertEquals(createWhiteRook(), board.findPiece("h1"));
    }

    @Test
    @DisplayName("주어진 좌표로 임의의 기물을 추가한다.")
    public void put() throws Exception {
        board.initializeEmpty();

        String position = "b5";
        Piece piece = createBlackRook();
        board.put(piece, position);

        assertEquals(piece, board.findPiece(position));
    }

    @Test
    @DisplayName("현재 체스판에 남아있는 기물들의 점수를 계산한다.")
    public void calculatePoint() throws Exception {
        board.initializeEmpty();

        addPiece("b6", createBlackPawn());
        addPiece("e6", createBlackQueen());
        addPiece("b8", createBlackKing());
        addPiece("c8", createBlackRook());

        addPiece("f2", createWhitePawn());
        addPiece("g2", createWhitePawn());
        addPiece("g3", createWhitePawn());
        addPiece("g4", createWhitePawn());
        addPiece("e1", createWhiteRook());
        addPiece("f1", createWhiteKing());

        assertEquals(15.0, board.calculatePoint(BLACK), 0.01);
        assertEquals(7.5, board.calculatePoint(WHITE), 0.01);
    }

    @Test
    @DisplayName("기물별로 점수를 정렬한다.")
    void sortByPiecePoint() {
        // when
        board.initializeEmpty();

        addPiece("b1", createWhitePawn());
        addPiece("b2", createWhitePawn());
        addPiece("b3", createWhitePawn());
        addPiece("b4", createWhitePawn());
        addPiece("c5", createWhiteKnight());

        addPiece("b6", createBlackPawn());
        addPiece("e6", createBlackQueen());
        addPiece("b8", createBlackKing());
        addPiece("c8", createBlackRook());

        // then
        assertEquals(Arrays.asList(BISHOP, KING, QUEEN, ROOK, PAWN, KNIGHT), board.sortAscByPiecePoint(WHITE));
        assertEquals(Arrays.asList(QUEEN, ROOK, PAWN, KNIGHT, KING, BISHOP), board.sortDescByPiecePoint(BLACK));
    }

    private void addPiece(String position, Piece piece) {
        board.put(piece, position);
    }
}
