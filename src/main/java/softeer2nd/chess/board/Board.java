package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Piece;

import java.util.HashMap;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {
    public static final char EMPTY_REPRESENTATION = '.';
    public static final int WHITE_PAWN_ROW = 2;
    public static final int BLACK_PAWN_ROW = 7;
    public static final int WHITE_SPECIAL_ROW = 1;
    public static final int BLACK_SPECIAL_ROW = 8;
    public static final int START_POINT = 1;
    public static final int MAX_POINT = 9;
    private HashMap<String, Piece> board;

    public Board() {
        board = new HashMap<>();
    }

    public void initialize() {
        board = new HashMap<>();
        initializeKing();
        initializeQueen();
        initializeRook();
        initializeBishop();
        initializeKnight();
        initializePawn();
    }

    private void initializePawn() {
        for (int y = START_POINT; y < MAX_POINT; y++) {
            add(convertPoint(WHITE_PAWN_ROW, y), Piece.createWhitePawn());
            add(convertPoint(BLACK_PAWN_ROW, y), Piece.createBlackPawn());
        }
    }

    private void initializeKing() {
        add(convertPoint(WHITE_SPECIAL_ROW, 5), Piece.createWhiteKing());
        add(convertPoint(BLACK_SPECIAL_ROW, 5), Piece.createBlackKing());
    }

    private void initializeQueen() {
        add(convertPoint(WHITE_SPECIAL_ROW, 4), Piece.createWhiteQueen());
        add(convertPoint(BLACK_SPECIAL_ROW, 4), Piece.createBlackQueen());
    }

    private void initializeRook() {
        add(convertPoint(WHITE_SPECIAL_ROW, 1), Piece.createWhiteRook());
        add(convertPoint(WHITE_SPECIAL_ROW, 8), Piece.createWhiteRook());
        add(convertPoint(BLACK_SPECIAL_ROW, 1), Piece.createBlackRook());
        add(convertPoint(BLACK_SPECIAL_ROW, 8), Piece.createBlackRook());
    }

    private void initializeBishop() {
        add(convertPoint(WHITE_SPECIAL_ROW, 3), Piece.createWhiteBishop());
        add(convertPoint(WHITE_SPECIAL_ROW, 6), Piece.createWhiteBishop());
        add(convertPoint(BLACK_SPECIAL_ROW, 3), Piece.createBlackBishop());
        add(convertPoint(BLACK_SPECIAL_ROW, 6), Piece.createBlackBishop());
    }

    private void initializeKnight() {
        add(convertPoint(WHITE_SPECIAL_ROW, 2), Piece.createWhiteKnight());
        add(convertPoint(WHITE_SPECIAL_ROW, 7), Piece.createWhiteKnight());
        add(convertPoint(BLACK_SPECIAL_ROW, 2), Piece.createBlackKnight());
        add(convertPoint(BLACK_SPECIAL_ROW, 7), Piece.createBlackKnight());
    }

    private String convertPoint(int x, int y) {
        if (isCorrectPoint(x, y)) {
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(x).append(y).toString();
        }
        throw new IllegalArgumentException("올바른 좌표값이 아닙니다.");
    }

    private boolean isCorrectPoint(int x, int y) {
        if (x < START_POINT || x >= MAX_POINT) {
            return false;
        }
        if (y < START_POINT || y >= MAX_POINT) {
            return false;
        }
        return true;
    }

    private int convertRow(String point) {
        return Character.getNumericValue(point.charAt(0));
    }

    private int convertColumn(String point) {
        return Character.getNumericValue(point.charAt(1));
    }

    public String showBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        /* 마지막 행부터 출력하여 출력 순서를 맞춰준다. */
        for (int x = MAX_POINT - 1; x >= START_POINT; x--) {
            stringBuilder.append(getRowResult(x));
        }
        return stringBuilder.toString();
    }

    private String getRowResult(int rowNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = START_POINT; y < MAX_POINT; y++) {
            stringBuilder.append(getRepresentation(rowNumber, y));
        }
        return appendNewLine(stringBuilder.toString());
    }

    private char getRepresentation(int x, int y) {
        if (board.containsKey(convertPoint(x, y))) {
            return board.get(convertPoint(x, y)).getRepresentation();
        }
        return EMPTY_REPRESENTATION;
    }

    public void add(String point, Piece piece) {
        this.board.put(point, piece);
    }

    public int pieceCount() {
        int pieceCount = 0;
        for (int x = START_POINT; x < MAX_POINT; x++) {
            for (int y = START_POINT; y < MAX_POINT; y++) {
                if (board.containsKey(convertPoint(x, y))) {
                    pieceCount++;
                }
            }
        }
        return pieceCount;
    }
}