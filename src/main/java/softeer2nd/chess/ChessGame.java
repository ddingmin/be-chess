package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;
import softeer2nd.chess.pieces.Piece;

import java.util.*;
import java.util.stream.IntStream;

public class ChessGame {
    public static final int START_POSITION = 0;
    public static final int MAX_POSITION = 8;
    public static final double PENALTY_PAWN_POINT = 0.5;
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    public void initialize() {
        board.initialize();
    }

    public void initializeEmpty() {
        board.initializeEmpty();
    }

    public void move(Position sourcePosition, Position targetPosition) {
        board.put(board.findPiece(sourcePosition), targetPosition);
        board.put(Piece.createBlank(), sourcePosition);
    }

    public List<Piece.Type> sortAscByPiecePoint(Piece.Color color) {
        Map<Piece.Type, Double> pointByPieces = createPointByPieces(color);
        List<Piece.Type> sortedKeys = new ArrayList<>(pointByPieces.keySet());

        sortedKeys.sort(Comparator.comparingDouble(pointByPieces::get)
                .thenComparing(Object::toString));

        return sortedKeys;
    }

    public List<Piece.Type> sortDescByPiecePoint(Piece.Color color) {
        Map<Piece.Type, Double> pointByPieces = createPointByPieces(color);
        List<Piece.Type> sortedKeys = new ArrayList<>(pointByPieces.keySet());

        sortedKeys.sort(Comparator.comparingDouble(pointByPieces::get)
                .thenComparing(Object::toString)
                .reversed());
        return sortedKeys;
    }

    public double calculatePoint(Piece.Color color) {
        double point = 0.0;
        point += calculateByGeneralRule(Piece.createKing(color));
        point += calculateByGeneralRule(Piece.createQueen(color));
        point += calculateByGeneralRule(Piece.createRook(color));
        point += calculateByGeneralRule(Piece.createBishop(color));
        point += calculateByGeneralRule(Piece.createKnight(color));
        point += calculateByPenaltyRule(Piece.createPawn(color));
        return point;
    }

    private Map<Piece.Type, Double> createPointByPieces(Piece.Color color) {
        Map<Piece.Type, Double> pointByPieces = new HashMap<>();

        pointByPieces.put(Piece.Type.KING, calculateByGeneralRule(Piece.createKing(color)));
        pointByPieces.put(Piece.Type.QUEEN, calculateByGeneralRule(Piece.createQueen(color)));
        pointByPieces.put(Piece.Type.ROOK, calculateByGeneralRule(Piece.createRook(color)));
        pointByPieces.put(Piece.Type.BISHOP, calculateByGeneralRule(Piece.createBishop(color)));
        pointByPieces.put(Piece.Type.KNIGHT, calculateByGeneralRule(Piece.createKnight(color)));
        pointByPieces.put(Piece.Type.PAWN, calculateByPenaltyRule(Piece.createPawn(color)));
        return pointByPieces;
    }

    private double calculateByGeneralRule(Piece piece) {
        return board.pieceCount(piece) * piece.getDefaultPoint();
    }

    private double calculateByPenaltyRule(Piece piece) {
        return IntStream.range(START_POSITION, MAX_POSITION)
                .mapToDouble(file -> calculateFilePoint(piece, file))
                .sum();
    }

    private double calculateFilePoint(Piece piece, int file) {
        if (board.pieceFileCount(piece, file) == 1) {
            return piece.getDefaultPoint();
        }
        return board.pieceFileCount(piece, file) * PENALTY_PAWN_POINT;
    }
}
