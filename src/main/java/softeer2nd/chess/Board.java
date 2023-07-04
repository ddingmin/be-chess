package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Board {
    private HashMap<Point, Pawn> board;
    private List<Point> points;
    public static final int WHITE_PAWN_ROW = 7;
    public static final int BLACK_PAWN_ROW = 2;
    public static final int START_POINT = 1;
    public static final int MAX_POINT = 9;
    public static final String NEW_LINE = "\n";
    public static final String DOT = ".";

    public Board() {
        board = new HashMap<>();
        points = new ArrayList<>();
        for (int i = START_POINT; i < MAX_POINT; i++) {
            for (int j = START_POINT; j < MAX_POINT; j++) {
                Point point = new Point(i, j);
                points.add(point);
            }
        }
    }

    public void add(Pawn pawn, int x, int y) {
        this.board.put(getPoint(x, y), pawn);
    }

    public int size() {
        return board.size();
    }

    public Pawn findPawn(int index) {
        return board.get(index);
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
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() == WHITE_PAWN_ROW) {
                board.put(points.get(i), new Pawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION));
            }
            if (points.get(i).getX() == BLACK_PAWN_ROW) {
                board.put(points.get(i), new Pawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION));
            }
        }
    }

    public String getBoardResult() {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = START_POINT; i < MAX_POINT; i++) {
            for (int j = START_POINT; j < MAX_POINT; j++) {
                if (board.get(getPoint(i, j)) == null) {
                    stringBuilder.append(DOT);
                } else if (board.get(getPoint(i, j)) != null) {
                    stringBuilder.append(board.get(getPoint(i, j)).getRepresentation());
                }
            }
            stringBuilder.append(NEW_LINE);
        }
        return stringBuilder.toString();
    }

    public String getWhitePawnsResult() {
        return getRowResult(WHITE_PAWN_ROW);
    }

    public String getBlackPawnsResult() {
        return getRowResult(BLACK_PAWN_ROW);
    }

    private String getRowResult(int rowNumber) {
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() == rowNumber && board.get(points.get(i)) != null) {
                stringBuilder.append(board.get(points.get(i)).getRepresentation());
            } else if (points.get(i).getX() == rowNumber && board.get(points.get(i)) == null) {
                stringBuilder.append(DOT);
            }
        }
        return stringBuilder.toString();
    }

    public Point getPoint(int x, int y) {
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i).getX() == x && points.get(i).getY() == y) {
                return points.get(i);
            }
        }
        throw new IllegalArgumentException("좌표값이 올바르지 않습니다.");
    }
}
