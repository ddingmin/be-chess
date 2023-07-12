package softeer2nd.chess.pieces;

import softeer2nd.chess.board.Position;

public class Blank extends Piece{
    private Blank(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create() {
        return new Blank(Color.NOCOLOR, Type.NO_PIECE);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        throw new IllegalArgumentException("이동시킬 좌표에 기물이 존재하지 않습니다.");
    }
}
