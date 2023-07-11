package softeer2nd.chess.pieces;

public class Pawn extends Piece{
    private Pawn(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Pawn(color, Type.PAWN);
    }
}