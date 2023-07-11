package softeer2nd.chess.pieces;

public class Knight extends Piece{
    private Knight(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Knight(color, Type.KNIGHT);
    }
}
