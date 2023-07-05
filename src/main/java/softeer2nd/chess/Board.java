package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.utils.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private HashMap<Point, Piece> board;
    private List<Point> points;
    private List<Piece> pieces;
    public static final int WHITE_PAWN_ROW = 7;
    public static final int BLACK_PAWN_ROW = 2;
    public static final int START_POINT = 1;
    public static final int MAX_POINT = 9;
    public static final String DOT = ".";

    public Board() {
        board = new HashMap<>();
        points = new ArrayList<>();
        pieces = new ArrayList<>();
        for (int i = START_POINT; i < MAX_POINT; i++) {
            for (int j = START_POINT; j < MAX_POINT; j++) {
                Point point = new Point(i, j);
                points.add(point);
            }
        }
    }

    public void add(Piece piece, int x, int y) {
        this.board.put(getPoint(x, y), piece);
        this.pieces.add(piece);
    }

    public int size() {
        return board.size();
    }

    public Piece findPawn(int index) {
        return pieces.get(index);
    }

    public void initialize() {
        board = new HashMap<>();
        points = new ArrayList<>();
        for (int i = START_POINT; i < MAX_POINT; i++) {
            for (int j = START_POINT; j < MAX_POINT; j++) {
                Point point = new Point(i, j);
                points.add(point);
                board.put(point, null);
            }
        }
        initializePawnRow();
    }

    public void initializePawnRow() {
        for (Point point : points) {
            if (point.getX() == WHITE_PAWN_ROW) {
                board.put(point, Piece.createWhitePawn());
            }
            if (point.getX() == BLACK_PAWN_ROW) {
                board.put(point, Piece.createBlackPawn());
            }
        }
    }

    public String getBoardResult() {
        StringBuilder stringBuilder = new StringBuilder("");
        String result = "";
        for (int i = START_POINT; i < MAX_POINT; i++) {
            for (int j = START_POINT; j < MAX_POINT; j++) {
                if (board.get(getPoint(i, j)) == null) {
                    result = stringBuilder.append(DOT).toString();
                } else if (board.get(getPoint(i, j)) != null) {
                    result = stringBuilder.append(board.get(getPoint(i, j)).getRepresentation()).toString();
                }
            }
            stringBuilder = new StringBuilder(StringUtils.appendNewLine(result));
        }
        return result;
    }

    public String getWhitePawnsResult() {
        return getRowResult(WHITE_PAWN_ROW);
    }

    public String getBlackPawnsResult() {
        return getRowResult(BLACK_PAWN_ROW);
    }

    private String getRowResult(int rowNumber) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (Point point : points) {
            if (point.getX() == rowNumber && board.get(point) != null) {
                stringBuilder.append(board.get(point).getRepresentation());
            } else if (point.getX() == rowNumber && board.get(point) == null) {
                stringBuilder.append(DOT);
            }
        }
        return stringBuilder.toString();
    }

    public Point getPoint(int x, int y) {
        for (Point point : points) {
            if (point.getX() == x && point.getY() == y) {
                return point;
            }
        }
        throw new IllegalArgumentException("좌표값이 올바르지 않습니다.");
    }
}
