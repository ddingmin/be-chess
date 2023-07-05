package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.PieceUtils.*;

@DisplayName("기물 테스트")
class PieceTest {
    @Test
    @DisplayName("입력한 색의 기물이 생성되어야 한다, 기물과 색에 따라 올바른 출력을 해야 한다.")
    void create() {
        verifyPawn(WHITE, WHITE_PAWN);
        verifyPawn(BLACK, BLACK_PAWN);
        verifyPawn(WHITE, WHITE_QUEEN);
        verifyPawn(BLACK, BLACK_QUEEN);
    }

    private void verifyPawn(final String color, final char representation) {
        Piece piece = new Piece(color, representation);
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}