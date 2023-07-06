package softeer2nd.chess.pieces;

import static softeer2nd.chess.utils.PieceUtils.*;

public class Piece {
    private final String color;
    private final char representation;
    private final String name;

    private Piece(String color, char representation) {
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
        return representation == BLACK_KING_REPRESENTATION || representation == WHITE_KING_REPRESENTATION;
    }

    private boolean isQueen(char representation) {
        return representation == BLACK_QUEEN_REPRESENTATION || representation == WHITE_QUEEN_REPRESENTATION;
    }

    private boolean isKnight(char representation) {
        return representation == BLACK_KNIGHT_REPRESENTATION || representation == WHITE_KNIGHT_REPRESENTATION;
    }

    private boolean isRook(char representation) {
        return representation == BLACK_ROOK_REPRESENTATION || representation == WHITE_ROOK_REPRESENTATION;
    }

    private boolean isBishop(char representation) {
        return representation == BLACK_BISHOP_REPRESENTATION || representation == WHITE_BISHOP_REPRESENTATION;
    }

    private boolean isPawn(char representation) {
        return representation == BLACK_PAWN_REPRESENTATION || representation == WHITE_PAWN_REPRESENTATION;
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE, WHITE_KING_REPRESENTATION);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE, WHITE_QUEEN_REPRESENTATION);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE, WHITE_BISHOP_REPRESENTATION);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE, WHITE_ROOK_REPRESENTATION);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE, WHITE_KNIGHT_REPRESENTATION);
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE, WHITE_PAWN_REPRESENTATION);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK, BLACK_KING_REPRESENTATION);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK, BLACK_QUEEN_REPRESENTATION);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK, BLACK_BISHOP_REPRESENTATION);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK, BLACK_ROOK_REPRESENTATION);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK, BLACK_KNIGHT_REPRESENTATION);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK, BLACK_PAWN_REPRESENTATION);
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

    public boolean isWhite() {
        return color.equals(WHITE);
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }
}
