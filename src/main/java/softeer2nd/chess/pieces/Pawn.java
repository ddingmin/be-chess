package softeer2nd.chess.pieces;

import softeer2nd.chess.board.Position;

public class Pawn extends Piece{
    private Pawn(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Pawn(color, Type.PAWN);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        // TODO: 차이를 찾는 로직은 direction으로 옮겨주자.
        int differenceRank = targetPosition.getRank() - sourcePosition.getRank();
        int differenceFile = targetPosition.getFile() - sourcePosition.getFile();

        for (Direction direction : Direction.getPawnDirection(getColor())) {
            if (isExistCorrectPoint(direction, differenceRank, differenceFile)) {
                return;
            }
        }

        throw new IllegalArgumentException("선택된 기물이 이동할 수 없는 좌표입니다.");
    }
}
