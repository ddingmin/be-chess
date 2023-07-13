package softeer2nd.chess.pieces;

import softeer2nd.chess.position.Position;

public class Knight extends Piece{
    private Knight(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Knight(color, Type.KNIGHT);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int differenceFile = targetPosition.getFile() - sourcePosition.getFile();
        int differenceRank = targetPosition.getRank() - sourcePosition.getRank();

        for (Direction direction : Direction.knightDirection()) {
            if (isExistCorrectPoint(direction, differenceRank, differenceFile)) {
                return;
            }
        }

        throw new IllegalArgumentException("선택된 기물이 이동할 수 없는 좌표입니다.");
    }
}
