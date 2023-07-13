package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.Color;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;
import softeer2nd.chess.position.Position;

import java.util.*;
import java.util.stream.IntStream;

public class ChessGame {
    public static final int START_POSITION = 0;
    public static final int MAX_POSITION = 8;
    public static final double PENALTY_PAWN_POINT = 0.5;
    private final Board board;
    private Color turn;

    public ChessGame(Board board, Color turn) {
        this.board = board;
        this.turn = turn;
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
        changeTurn();
    }

    private void verifyMove(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        verifyTurn(sourcePiece);
        verifyExistSameColor(sourcePosition, targetPosition);

        if (!sourcePiece.isMovingPiece()) {
            return;
        }
        verifyExistPieceOnRoute(sourcePosition, targetPosition);
    }

    private void verifyTurn(Piece sourcePiece) {
        if (turn.equals(sourcePiece.getColor())) {
            return;
        }
        throw new RuntimeException("현재 " + turn.toString() + " 플레이어의 턴입니다.");
    }

    private void verifyExistSameColor(Position sourcePosition, Position targetPosition) {
        if (isSameColor(sourcePosition, targetPosition)) {
            throw new RuntimeException("이동할 위치에 같은 색 기물이 존재합니다.");
        }
    }

    private boolean isSameColor(Position sourcePosition, Position targetPosition) {
        return board.findPiece(sourcePosition).getColor()
                .equals(board.findPiece(targetPosition).getColor());
    }

    private void verifyExistPieceOnRoute(Position sourcePosition, Position targetPosition) {
        int directionRank = Integer.compare(targetPosition.getRank() - sourcePosition.getRank(), 0);
        int directionFile = Integer.compare(targetPosition.getFile() - sourcePosition.getFile(), 0);

        verifyExistPiece(sourcePosition, targetPosition, directionRank, directionFile);
    }

    private void verifyExistPiece(Position position, Position targetPosition, int directionRank, int directionFile) {
        Position nextPosition = new Position(position.getRank() + directionRank, position.getFile() + directionFile);

        if (targetPosition.equals(nextPosition)) {
            return;
        }

        if (!board.findPiece(nextPosition).isBlank()) {
            throw new RuntimeException("이동 경로에 기물이 존재합니다.");
        }
        verifyExistPiece(nextPosition, targetPosition, directionRank, directionFile);
    }

    private void changeTurn() {
        if (turn.equals(Color.WHITE)) {
            turn = Color.BLACK;
            return;
        }
        turn = Color.WHITE;
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
