package softeer2nd.chess.board;

public class Position {
    private final int file;
    private final int rank;

    public Position(String position) {
        validatePosition(position);

        this.file = convertPositionToFile(position.charAt(0));
        this.rank = convertPositionToRank(position.charAt(1));
    }

    private void validatePosition(String position) {
        if (position.length() != 2) {
            throw new IllegalArgumentException("올바른 좌표 형태가 아닙니다.");
        }
        if (position.charAt(0) < 'a' || position.charAt(0) > 'h') {
            throw new IllegalArgumentException("올바른 file이 아닙니다.");
        }
        if (position.charAt(1) < '1' || position.charAt(1) > '8') {
            throw new IllegalArgumentException("올바른 rank가 아닙니다.");
        }
    }

    private int convertPositionToFile(final char file) {
        return file - 'a';
    }

    private int convertPositionToRank(final char rank) {
        return Character.getNumericValue(rank - 1);
    }

    public int getFile() {
        return file;
    }

    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return Integer.toString(file + 'a') + Integer.toString(rank);
    }
}
