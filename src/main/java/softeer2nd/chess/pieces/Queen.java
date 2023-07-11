package softeer2nd.chess.pieces;

public class Queen extends Piece{
    private Queen(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Queen(color, Type.QUEEN);
    }
}
