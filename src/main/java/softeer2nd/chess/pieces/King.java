package softeer2nd.chess.pieces;

public class King extends Piece{
    private King(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new King(color, Type.KING);
    }
}
