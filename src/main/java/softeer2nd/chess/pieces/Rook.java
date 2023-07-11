package softeer2nd.chess.pieces;

public class Rook extends Piece{
    private Rook(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Rook(color, Type.ROOK);
    }
}
