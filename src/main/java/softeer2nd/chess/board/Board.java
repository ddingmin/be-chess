package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Piece;

import java.util.*;
import java.util.stream.IntStream;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {
    public static final int WHITE_PAWN_ROW = 1;
    public static final int BLACK_PAWN_ROW = 6;
    public static final int WHITE_SPECIAL_ROW = 0;
    public static final int BLACK_SPECIAL_ROW = 7;
    public static final int START_POSITION = 0;
    public static final int MAX_POSITION = 8;
    public static final double PENALTY_PAWN_POINT = 0.5;

    private List<Rank> board;
    private Map<Piece.Type, Double> pointByPiece;

    public Board() {
        initializeEmpty();
        initializePointByPiece();
    }

    public void initialize() {
        initializeEmpty();
        initializeSpecialPiece();
        initializePawn();
    }

    public void initializeEmpty() {
        board = new ArrayList<>();
        IntStream.range(START_POSITION, MAX_POSITION)
                .forEach(rank -> board.add(Rank.createEmpty()));
    }

    private void initializeSpecialPiece() {
        board.set(WHITE_SPECIAL_ROW, Rank.createWhiteSpecialPieces());
        board.set(BLACK_SPECIAL_ROW, Rank.createBlackSpecialPieces());
    }

    private void initializePawn() {
        board.set(WHITE_PAWN_ROW, Rank.createWhitePawns());
        board.set(BLACK_PAWN_ROW, Rank.createBlackPawns());
    }

    private void initializePointByPiece() {
        pointByPiece = new HashMap<>();
    }

    private String convertPoint(int rank, int file) {
        if (isCorrectPoint(rank, file)) {
            StringBuilder stringBuilder = new StringBuilder();
            return stringBuilder.append(rank).append(file).toString();
        }
        throw new IllegalArgumentException("올바른 좌표값이 아닙니다.");
    }

    private boolean isCorrectPoint(int rank, int file) {
        return (rank >= START_POSITION && rank < MAX_POSITION)
                && (file >= START_POSITION && file < MAX_POSITION);
    }

    public String showBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        /* 마지막 행부터 출력하여 출력 순서를 맞춰준다. */
        for (int rank = MAX_POSITION - 1; rank >= START_POSITION; rank--) {
            stringBuilder.append(getRankResult(rank));
        }
        return stringBuilder.toString();
    }

    private String getRankResult(int rowNumber) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = START_POSITION; y < MAX_POSITION; y++) {
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
        return IntStream.range(START_POSITION, MAX_POSITION)
                .map(this::pieceFileCount)
                .sum();
    }

    public int pieceCount(Piece.Type type, Piece.Color color) {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .map(file -> pieceFileCount(color, type, file))
                .sum();
    }

    public int pieceCount(Piece.Color color) {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .map(file -> pieceFileCount(color, file))
                .sum();
    }

    private int pieceFileCount(Piece.Color color, Piece.Type type, int file) {
        return (int) board.stream()
                .filter(rank -> rank.getPiece(file).getColor().equals(color)
                && rank.getPiece(file).getType().equals(type))
                .count();
    }

    private int pieceFileCount(Piece.Color color, int file) {
        return (int) board.stream()
                .filter(rank -> rank.getPiece(file).getColor().equals(color))
                .count();
    }

    private int pieceFileCount(int file) {
        return (int) board.stream()
                .filter(rank -> !rank.getPiece(file).getType().equals(Piece.Type.NO_PIECE))
                .count();
    }

    public Piece findPiece(String position) {
        int rank = getPositionToRank(position);
        int file = getPositionToFile(position);
        return board.get(rank)
                .getPiece(file);
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0.0;
        point += calculateKing(color);
        point += calculateQueen(color);
        point += calculateRook(color);
        point += calculateBishop(color);
        point += calculateKnight(color);
        point += calculatePawn(color);
        return point;
    }

    private double calculateKing(Piece.Color color) {
        return pieceCount(Piece.Type.KING, color) * Piece.Type.KING.getDefaultPoint();
    }

    private double calculateQueen(Piece.Color color) {
        return pieceCount(Piece.Type.QUEEN, color) * Piece.Type.QUEEN.getDefaultPoint();
    }

    private double calculateBishop(Piece.Color color) {
        return pieceCount(Piece.Type.BISHOP, color) * Piece.Type.BISHOP.getDefaultPoint();
    }

    private double calculateRook(Piece.Color color) {
        return pieceCount(Piece.Type.ROOK, color) * Piece.Type.ROOK.getDefaultPoint();
    }

    private double calculateKnight(Piece.Color color) {
        return pieceCount(Piece.Type.KNIGHT, color) * Piece.Type.KNIGHT.getDefaultPoint();
    }

    private double calculatePawn(Piece.Color color) {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .mapToDouble(file -> calculateFilePoint(color, Piece.Type.PAWN, file))
                .sum();
    }

    private double calculateFilePoint(Piece.Color color, Piece.Type type, int file) {
        if (pieceFileCount(color, type, file) == 1) {
            return type.getDefaultPoint();
        }
        return pieceFileCount(color, type, file) * PENALTY_PAWN_POINT;
    }

    public List<Piece.Type> sortAscByPiecePoint(Piece.Color color) {
        setPointByPiece(color);

        List<Piece.Type> sortedKeys = new ArrayList<>(pointByPiece.keySet());
        sortedKeys.sort(Comparator.comparingDouble(pointByPiece::get)
                .thenComparing(Object::toString));

        return sortedKeys;
    }

    public List<Piece.Type> sortDescByPiecePoint(Piece.Color color) {
        setPointByPiece(color);

        List<Piece.Type> sortedKeys = new ArrayList<>(pointByPiece.keySet());
        sortedKeys.sort(Comparator.comparingDouble(pointByPiece::get)
                .thenComparing(Object::toString)
                .reversed());
        return sortedKeys;
    }

    private void setPointByPiece(Piece.Color color) {
        pointByPiece.put(Piece.Type.KING, calculateKing(color));
        pointByPiece.put(Piece.Type.QUEEN, calculateQueen(color));
        pointByPiece.put(Piece.Type.ROOK, calculateRook(color));
        pointByPiece.put(Piece.Type.BISHOP, calculateBishop(color));
        pointByPiece.put(Piece.Type.KNIGHT, calculateKnight(color));
        pointByPiece.put(Piece.Type.PAWN, calculatePawn(color));
    }
}