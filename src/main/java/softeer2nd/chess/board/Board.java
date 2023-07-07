package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {
    public static final int WHITE_PAWN_ROW = 1;
    public static final int BLACK_PAWN_ROW = 6;
    public static final int WHITE_SPECIAL_ROW = 0;
    public static final int BLACK_SPECIAL_ROW = 7;
    public static final int START_POINT = 0;
    public static final int MAX_POINT = 8;

    private List<Rank> board;

    public Board() {
        initializeEmpty();
    }

    public void initialize() {
        initializeEmpty();
        initializeSpecialPiece();
        initializePawn();
    }

    public void initializeEmpty() {
        board = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            board.add(Rank.createEmpty());
        }
    }

    private void initializeSpecialPiece() {
        board.set(WHITE_SPECIAL_ROW, Rank.createWhiteSpecialPiece());
        board.set(BLACK_SPECIAL_ROW, Rank.createBlackSpecialPiece());
    }

    private void initializePawn() {
        board.set(WHITE_PAWN_ROW, Rank.createWhitePawn());
        board.set(BLACK_PAWN_ROW, Rank.createBlackPawn());
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

    private char getRepresentation(int rank, int file) {
        return board.get(rank).getPiece(file).getRepresentation();
    }

    public void put(Piece piece, int rank, int file) {
        board.get(rank).putPiece(piece, file);
    }

    public void put(Piece piece, String position) {
        int rank = getPositionToRank(position);
        int file = getPositionToFile(position);
        board.get(rank).putPiece(piece, file);
    }

    private int getPositionToFile(String position) {
        return (position.charAt(0) - 'a');
    }

    private int getPositionToRank(String position) {
        return Character.getNumericValue(position.charAt(1) - 1);
    }

    public int pieceAllCount() {
        int pieceCount = 0;

        for (int rank = START_POINT; rank < MAX_POINT; rank++) {
            for (int file = START_POINT; file < MAX_POINT; file++) {
                if (!board.get(rank).getPiece(file).getType().equals(Piece.Type.NO_PIECE)) {
                    pieceCount++;
                }
            }
        }
        return pieceCount;
    }

    public int pieceCount(Piece.Type type, Piece.Color color) {
        int pieceCount = 0;

        for (int rank = START_POINT; rank < MAX_POINT; rank++) {
            for (int file = START_POINT; file < MAX_POINT; file++) {
                if (board.get(rank).getPiece(file).getType().equals(type)
                        && board.get(rank).getPiece(file).getColor().equals(color)) {
                    pieceCount++;
                }
            }
        }
        return pieceCount;
    }

    public int pieceCount(Piece.Color color) {
        int pieceCount = 0;

        for (int rank = START_POINT; rank < MAX_POINT; rank++) {
            for (int file = START_POINT; file < MAX_POINT; file++) {
                if (board.get(rank).getPiece(file).getColor().equals(color)) {
                    pieceCount++;
                }
            }
        }
        return pieceCount;
    }

    public Piece findPiece(String position) {
        int rank = getPositionToRank(position);
        int file = getPositionToFile(position);
        return board.get(rank).getPiece(file);
    }
}