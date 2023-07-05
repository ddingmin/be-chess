package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.PieceUtils.*;

@DisplayName("기물 테스트")
class PieceTest {
    @Test
    @DisplayName("입력한 색의 기물이 생성되어야 한다, 기물과 색에 따라 올바른 출력을 해야 한다.")
    void create_piece() {
        verifyPiece(Piece.createWhitePawn(), WHITE, WHITE_PAWN_REPRESENTATION);
        verifyPiece(Piece.createBlackPawn(), BLACK, BLACK_PAWN_REPRESENTATION);
        verifyPiece(Piece.createWhiteKing(), WHITE, WHITE_KING_REPRESENTATION);
        verifyPiece(Piece.createWhiteQueen(), WHITE, WHITE_QUEEN_REPRESENTATION);
        verifyPiece(Piece.createWhiteBishop(), WHITE, WHITE_BISHOP_REPRESENTATION);
        verifyPiece(Piece.createWhiteRook(), WHITE, WHITE_ROOK_REPRESENTATION);
        verifyPiece(Piece.createWhiteKnight(), WHITE, WHITE_KNIGHT_REPRESENTATION);
    }

    private void verifyPiece(final Piece piece, final String color, final char representation) {
        assertEquals(color, piece.getColor());
        assertEquals(representation, piece.getRepresentation());
    }
}