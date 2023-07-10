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

    private List<Rank> ranks;
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
        ranks = new ArrayList<>();
        IntStream.range(START_POSITION, MAX_POSITION)
                .forEach(rank -> ranks.add(Rank.createEmpty()));
    }

    private void initializeSpecialPiece() {
        ranks.set(WHITE_SPECIAL_ROW, Rank.createWhiteSpecialPieces());
        ranks.set(BLACK_SPECIAL_ROW, Rank.createBlackSpecialPieces());
    }

    private void initializePawn() {
        ranks.set(WHITE_PAWN_ROW, Rank.createWhitePawns());
        ranks.set(BLACK_PAWN_ROW, Rank.createBlackPawns());
    }

    private void initializePointByPiece() {
        pointByPiece = new HashMap<>();
    }

    public String getBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        /* 마지막 행부터 출력하여 출력 순서를 맞춰준다. */
        for (int rank = MAX_POSITION - 1; rank >= START_POSITION; rank--) {
            stringBuilder.append(getRank(rank));
        }
        return stringBuilder.toString();
    }

    private String getRank(int rank) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int file = START_POSITION; file < MAX_POSITION; file++) {
            stringBuilder.append(findPiece(rank, file).getRepresentation());
        }
        return appendNewLine(stringBuilder.toString());
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getRank())
                .getPiece(position.getFile());
    }

    private Piece findPiece(int rank, int file) {
        return ranks.get(rank).getPiece(file);
    }

    public int pieceAllCount() {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .map(this::pieceFileCount)
                .sum();
    }

    public int pieceCount(Piece piece) {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .map(file -> pieceFileCount(piece, file))
                .sum();
    }

    public int pieceCount(Piece.Color color) {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .map(file -> pieceFileCountByColor(color, file))
                .sum();
    }

    private int pieceFileCount(Piece piece, int file) {
        return (int) ranks.stream()
                .filter(rank -> rank.getPiece(file).equals(piece))
                .count();
    }

    private int pieceFileCountByColor(Piece.Color color, int file) {
        return (int) ranks.stream()
                .filter(rank -> rank.getPiece(file).getColor().equals(color))
                .count();
    }

    private int pieceFileCount(int file) {
        return (int) ranks.stream()
                .filter(rank -> !rank.getPiece(file).getType().equals(Piece.Type.NO_PIECE))
                .count();
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

    public double calculatePoint(Piece piece) {
        double point = 0.0;
        point += pieceCount(piece) * piece.getRepresentation();
        return point;
    }

    private double calculateKing(Piece.Color color) {
        return pieceCount(Piece.createKing(color)) * Piece.Type.KING.getDefaultPoint();
    }

    private double calculateQueen(Piece.Color color) {
        return pieceCount(Piece.createQueen(color)) * Piece.Type.QUEEN.getDefaultPoint();
    }

    private double calculateBishop(Piece.Color color) {
        return pieceCount(Piece.createBishop(color)) * Piece.Type.BISHOP.getDefaultPoint();
    }

    private double calculateRook(Piece.Color color) {
        return pieceCount(Piece.createRook(color)) * Piece.Type.ROOK.getDefaultPoint();
    }

    private double calculateKnight(Piece.Color color) {
        return pieceCount(Piece.createKnight(color)) * Piece.Type.KNIGHT.getDefaultPoint();
    }

    private double calculatePawn(Piece.Color color) {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .mapToDouble(file -> calculateFilePoint(Piece.createPawn(color), file))
                .sum();
    }

    private double calculateFilePoint(Piece piece, int file) {
        if (pieceFileCount(piece, file) == 1) {
            return piece.getDefaultPoint();
        }
        return pieceFileCount(piece, file) * PENALTY_PAWN_POINT;
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

    public void put(Piece piece, Position position) {
        int rank = position.getRank();
        int file = position.getFile();
        ranks.get(rank).putPiece(piece, file);
    }
}