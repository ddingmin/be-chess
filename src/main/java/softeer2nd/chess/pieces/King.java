package softeer2nd.chess.pieces;

import softeer2nd.chess.utils.Position;

public class King extends Piece{
    private King(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new King(color, Type.KING);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int differenceFile = targetPosition.getFile() - sourcePosition.getFile();
        int differenceRank = targetPosition.getRank() - sourcePosition.getRank();

        for (Direction direction : Direction.everyDirection()) {
            if (isExistCorrectPoint(direction, differenceRank, differenceFile)) {
                return;
            }
        }

        throw new IllegalArgumentException("해당 좌표로 이동할 수 없습니다.");
    }
}
