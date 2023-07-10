package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("기물 테스트")
class PieceTest {
    @Test
    @DisplayName("입력한 색의 기물이 생성되어야 한다, 기물과 색에 따라 올바른 출력을 해야 한다.")
    void createPiece() {
        verifyPiece(Piece.createBlackPawn(), Piece.Color.BLACK, Type.PAWN.getBlackRepresentation());
        verifyPiece(Piece.createWhitePawn(), Piece.Color.WHITE, Type.PAWN.getWhiteRepresentation());
        verifyPiece(Piece.createWhiteKing(), Piece.Color.WHITE, Type.KING.getWhiteRepresentation());
        verifyPiece(Piece.createWhiteQueen(), Piece.Color.WHITE, Type.QUEEN.getWhiteRepresentation());
        verifyPiece(Piece.createWhiteBishop(), Piece.Color.WHITE, Type.BISHOP.getWhiteRepresentation());
        verifyPiece(Piece.createWhiteRook(), Piece.Color.WHITE, Type.ROOK.getWhiteRepresentation());
        verifyPiece(Piece.createWhiteKnight(), Piece.Color.WHITE, Type.KNIGHT.getWhiteRepresentation());
    }

    @Test
    @DisplayName("기물이 존재하지 않는 기물도 생성되어야 한다.")
    void createBlankPiece() {
        verifyPiece(Piece.createBlank(), Piece.Color.NOCOLOR, Type.NO_PIECE.getBlackRepresentation());
    }

    @Test
    @DisplayName("기물의 색에 따라 올바른 representation 을 반환해야 한다.")
    void getRepresentationPerPiece() throws Exception {
        assertEquals('p', Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Type.PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("검은색 말과 흰색말을 구분해야 한다.")
    void check_color() {
        Piece whitePawn = Piece.createWhitePawn();
        Piece blackPawn = Piece.createBlackPawn();

        assertTrue(whitePawn.isWhite());
        assertTrue(blackPawn.isBlack());
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}