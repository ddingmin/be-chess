package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;
import softeer2nd.chess.pieces.Color;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;

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
        Piece sourcePiece = board.findPiece(sourcePosition);

        // 현재 기물이 이동할 수 있는지 좌표인지 검증
        sourcePiece.verifyMovePosition(sourcePosition, targetPosition);

        // 모든 로직이 완료되었다면 이동.
        board.put(sourcePiece, targetPosition);
        board.put(Piece.createBlank(), sourcePosition);
    }

    public List<Type> sortAscByPiecePoint(Color color) {
        Map<Type, Double> pointByPieces = createPointByPieces(color);
        List<Type> sortedKeys = new ArrayList<>(pointByPieces.keySet());

        sortedKeys.sort(Comparator.comparingDouble(pointByPieces::get)
                .thenComparing(Object::toString));

        return sortedKeys;
    }

    public List<Type> sortDescByPiecePoint(Color color) {
        Map<Type, Double> pointByPieces = createPointByPieces(color);
        List<Type> sortedKeys = new ArrayList<>(pointByPieces.keySet());

        sortedKeys.sort(Comparator.comparingDouble(pointByPieces::get)
                .thenComparing(Object::toString)
                .reversed());
        return sortedKeys;
    }

    public double calculatePoint(Color color) {
        double point = 0.0;
        point += calculateByGeneralRule(Piece.create(Type.KING, color));
        point += calculateByGeneralRule(Piece.create(Type.QUEEN, color));
        point += calculateByGeneralRule(Piece.create(Type.ROOK, color));
        point += calculateByGeneralRule(Piece.create(Type.BISHOP, color));
        point += calculateByGeneralRule(Piece.create(Type.KNIGHT, color));
        point += calculateByPenaltyRule(Piece.create(Type.PAWN, color));
        return point;
    }

    private Map<Type, Double> createPointByPieces(Color color) {
        Map<Type, Double> pointByPieces = new HashMap<>();

        pointByPieces.put(Type.KING, calculateByGeneralRule(Piece.create(Type.KING, color)));
        pointByPieces.put(Type.QUEEN, calculateByGeneralRule(Piece.create(Type.QUEEN, color)));
        pointByPieces.put(Type.ROOK, calculateByGeneralRule(Piece.create(Type.ROOK, color)));
        pointByPieces.put(Type.BISHOP, calculateByGeneralRule(Piece.create(Type.BISHOP, color)));
        pointByPieces.put(Type.KNIGHT, calculateByGeneralRule(Piece.create(Type.KNIGHT, color)));
        pointByPieces.put(Type.PAWN, calculateByPenaltyRule(Piece.create(Type.PAWN, color)));
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
