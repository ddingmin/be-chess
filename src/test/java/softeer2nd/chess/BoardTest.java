package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("체스판 테스트")
class BoardTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드 클래스를 테스트 한다. size(), findPawn() 메서드를 테스트한다.")
    void create() throws Exception {
        Pawn white = new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyAddPawn(white, 1, 0, 1, 8);

        Pawn black = new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
        verifyAddPawn(black, 2, 1, 2, 7);
    }

    @Test
    @DisplayName("보드에 폰 이외의 객체는 추가할 수 없다.")
    void create_다른객체() {
//        board.add(new Integer(7));
    }

    @Test
    @DisplayName("보드의 생성 테스트")
    void initialize() throws Exception {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    @Test
    @DisplayName("보드의 출력 테스트")
    void print() {
        String correctPrint = "........\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "........\n";
        board.initialize();
        assertEquals(board.getBoardResult(), correctPrint);
        System.out.println(board.getBoardResult());
    }

    void addPawn(Board board, Pawn pawn, int x, int y) {
        board.add(pawn, x, y);
    }

    void verifyAddPawn(Pawn pawn, int size, int index, int x, int y) {
        addPawn(board, pawn, x, y);
        assertEquals(size, board.size());
        assertEquals(pawn, board.findPawn(index));
    }
}
