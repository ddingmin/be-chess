package softeer2nd.chess.pieces;

import softeer2nd.chess.board.Position;

import java.util.List;

public class Pawn extends Piece{
    private Pawn(Color color, Type type) {
        super(color, type);
    }

    protected static Piece create(Color color) {
        return new Pawn(color, Type.PAWN);
    }

    @Override
    public void verifyMovePosition(Position sourcePosition, Position targetPosition) {
        int differenceFile = targetPosition.getFile() - sourcePosition.getFile();
        int differenceRank = targetPosition.getRank() - sourcePosition.getRank();

        for (Direction direction : getPawnDirection(getColor())) {
            if (direction.getXDegree() == differenceFile && direction.getYDegree() == differenceRank) {
                return;
            }
        }

        throw new IllegalArgumentException("선택된 기물이 이동할 수 없는 좌표입니다.");
    }

    // 색을 확인하는 로직은 Direction 으로 옮기자
    private List<Direction> getPawnDirection(Color color) {
        return color.equals(Color.BLACK) ? Direction.blackPawnDirection() : Direction.whitePawnDirection();
    }
}