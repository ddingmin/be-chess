package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static softeer2nd.chess.pieces.Color.*;
import static softeer2nd.chess.pieces.Type.*;

@DisplayName("기물 테스트")
class PieceTest {
    @Test
    @DisplayName("입력한 색의 기물이 생성되어야 한다, 기물과 색에 따라 올바른 출력을 해야 한다.")
    void createPiece() {
        verifyPiece(Piece.create(PAWN, BLACK), BLACK, PAWN.getBlackRepresentation());
        verifyPiece(Piece.create(PAWN, WHITE), WHITE, PAWN.getWhiteRepresentation());
        verifyPiece(Piece.create(KING,WHITE), WHITE, KING.getWhiteRepresentation());
        verifyPiece(Piece.create(QUEEN, WHITE), WHITE, QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.create(BISHOP, WHITE), WHITE, BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.create(ROOK, WHITE), WHITE, ROOK.getWhiteRepresentation());
        verifyPiece(Piece.create(KNIGHT, WHITE), WHITE, KNIGHT.getWhiteRepresentation());
    }

    @Test
    @DisplayName("기물이 존재하지 않는 기물도 생성되어야 한다.")
    void createBlankPiece() {
        verifyPiece(Piece.createBlank(), Color.NOCOLOR, NO_PIECE.getBlackRepresentation());
    }

    @Test
    @DisplayName("기물의 색에 따라 올바른 representation 을 반환해야 한다.")
    void getRepresentationPerPiece() throws Exception {
        assertEquals('p', PAWN.getWhiteRepresentation());
        assertEquals('P', PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("검은색 말과 흰색말을 구분해야 한다.")
    void check_color() {
        Piece whitePawn = Piece.create(PAWN, WHITE);
        Piece blackPawn = Piece.create(PAWN, BLACK);

        assertTrue(whitePawn.isWhite());
        assertTrue(blackPawn.isBlack());
    }

    private void verifyPiece(final Piece piece, final Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}