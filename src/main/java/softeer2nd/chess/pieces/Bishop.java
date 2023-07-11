package softeer2nd.chess.pieces;

public class Bishop extends Piece{
    private Bishop(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Bishop(color, Type.BISHOP);
    }
}
