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

        sourcePiece.verifyMovePosition(sourcePosition, targetPosition);
        verifyMove(sourcePosition, targetPosition, sourcePiece);

        board.put(sourcePiece, targetPosition);
        board.put(Piece.createBlank(), sourcePosition);
    }

    private void verifyMove(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        if (isSameColor(sourcePosition, targetPosition)) {
            throw new RuntimeException("이동할 위치에 같은 색 기물이 존재합니다.");
        }
        if (!isMovingPiece(sourcePiece)) {
            return;
        }
        if (isExistPieceOnRoute(sourcePosition, targetPosition)) {
            throw new RuntimeException("이동 경로에 기물이 존재해 이동할 수 없습니다.");
        }
    }

    private boolean isSameColor(Position sourcePosition, Position targetPosition) {
        return board.findPiece(sourcePosition).getColor()
                .equals(board.findPiece(targetPosition).getColor());
    }

    private boolean isExistPieceOnRoute(Position sourcePosition, Position targetPosition) {
        int bigFile = Math.max(targetPosition.getFile(), sourcePosition.getFile());
        int bigRank = Math.max(targetPosition.getRank(), sourcePosition.getRank());
        int smallFile = Math.min(targetPosition.getFile(), sourcePosition.getFile());
        int smallRank = Math.min(targetPosition.getRank(), sourcePosition.getRank());

        for (int file = smallFile; file < bigFile + 1; file++) {
            for (int rank = smallRank; rank < bigRank + 1; rank++) {
                if (isNeedlessPosition(sourcePosition, targetPosition, rank, file)) {
                    continue;
                }
                if (isExistPiece(rank, file)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isNeedlessPosition(Position sourcePosition, Position targetPosition, int rank, int file) {
        return (rank == sourcePosition.getRank() && file == sourcePosition.getFile())
                || rank == targetPosition.getRank() && file == targetPosition.getFile();
    }

    private boolean isExistPiece(int rank, int file) {
        return !board.findPiece(rank, file).getType().equals(Type.NO_PIECE);
    }

    private static boolean isMovingPiece(Piece sourcePiece) {
        return sourcePiece.getType().equals(Type.QUEEN) || sourcePiece.getType().equals(Type.ROOK) || sourcePiece.getType().equals(Type.BISHOP);
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
