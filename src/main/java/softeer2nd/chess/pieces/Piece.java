package softeer2nd.chess.pieces;

import static softeer2nd.chess.utils.PieceUtils.*;

public class Piece {
    private final String color;
    private final char representation;
    private final String name;

    public Piece(String color, char representation) {
        this.color = color;
        this.representation = representation;
        this.name = findName(representation);
    }

    private String findName(char representation) {
        if (isKing(representation)) {
            return KING;
        }
        if (isQueen(representation)) {
            return QUEEN;
        }
        if (isKnight(representation)) {
            return KNIGHT;
        }
        if (isBishop(representation)) {
            return BISHOP;
        }
        if (isRook(representation)) {
            return ROOK;
        }
        if (isPawn(representation)) {
            return PAWN;
        }
        throw new IllegalArgumentException("올바른 기물이 아닙니다.");
    }

    private boolean isKing(char representation) {
        if (representation == BLACK_KING || representation == WHITE_KING) {
            return true;
        }
        return false;
    }

    private boolean isQueen(char representation) {
        if (representation == BLACK_QUEEN || representation == WHITE_QUEEN) {
            return true;
        }
        return false;
    }

    private boolean isKnight(char representation) {
        if (representation == BLACK_KNIGHT || representation == WHITE_KNIGHT) {
            return true;
        }
        return false;
    }

    private boolean isRook(char representation) {
        if (representation == BLACK_ROOK || representation == WHITE_ROOK) {
            return true;
        }
        return false;
    }

    private boolean isBishop(char representation) {
        if (representation == BLACK_BISHOP || representation == WHITE_BISHOP) {
            return true;
        }
        return false;
    }

    private boolean isPawn(char representation) {
        if (representation == BLACK_PAWN || representation == WHITE_PAWN) {
            return true;
        }
        return false;
    }

    public String getColor() {
        return color;
    }

    public char getRepresentation() {
        return representation;
    }

    public String getName() {
        return name;
    }
}
