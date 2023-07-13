package softeer2nd.chess.pieces;

import softeer2nd.chess.utils.Position;

import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final Type type;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece create(Type type, Color color) {
        switch (color) {
            case BLACK:
                return createBlack(type);
            case WHITE:
                return createWhite(type);
            default:
                throw new IllegalArgumentException("올바르지 않은 color 입니다.");
        }
    }

    public static Piece createBlack(Type type) {
        switch (type) {
            case KING:
                return King.create(Color.BLACK);
            case QUEEN:
                return Queen.create(Color.BLACK);
            case ROOK:
                return Rook.create(Color.BLACK);
            case BISHOP:
                return Bishop.create(Color.BLACK);
            case KNIGHT:
                return Knight.create(Color.BLACK);
            case PAWN:
                return Pawn.create(Color.BLACK);
            default:
                throw new IllegalArgumentException("올바르지 않은 type 입니다.");
        }
    }

    public static Piece createWhite(Type type) {
        switch (type) {
            case KING:
                return King.create(Color.WHITE);
            case QUEEN:
                return Queen.create(Color.WHITE);
            case ROOK:
                return Rook.create(Color.WHITE);
            case BISHOP:
                return Bishop.create(Color.WHITE);
            case KNIGHT:
                return Knight.create(Color.WHITE);
            case PAWN:
                return Pawn.create(Color.WHITE);
            default:
                throw new IllegalArgumentException("올바르지 않은 type 입니다.");
        }
    }

    public static Piece createBlank() {
        return Blank.create();
    }

    public Color getColor() {
        return color;
    }

    public char getRepresentation() {
        if (color.equals(Color.WHITE)) {
            return type.getWhiteRepresentation();
        }
        return type.getBlackRepresentation();
    }

    public double getDefaultPoint() {
        return type.getDefaultPoint();
    }

    public Type getType() {
        return type;
    }

    public boolean isWhite() {
        return color.equals(Color.WHITE);
    }

    public boolean isBlack() {
        return color.equals(Color.BLACK);
    }

    public boolean isBlank() {
        return color.equals(Color.NOCOLOR);
    }

    public abstract void verifyMovePosition(Position sourcePosition, Position targetPosition);

    protected boolean isExistCorrectRoute(Direction direction, int differenceRank, int differenceFile) {
        for (int factor = 1; factor < 8; factor++) {
            if (direction.getXDegree() * factor == differenceFile
                    && direction.getYDegree() * factor == differenceRank) {
                return true;
            }
        }
        return false;
    }

    protected boolean isExistCorrectPoint(Direction direction, int differenceRank, int differenceFile) {
        return direction.getYDegree() == differenceRank && direction.getXDegree() == differenceFile;
    }

    public boolean isMovingPiece() {
        return getType().equals(Type.QUEEN) || getType().equals(Type.ROOK) || getType().equals(Type.BISHOP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type);
    }
}
