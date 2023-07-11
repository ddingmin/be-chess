package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Color;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {
    public static final int WHITE_PAWN_ROW = 1;
    public static final int BLACK_PAWN_ROW = 6;
    public static final int WHITE_SPECIAL_ROW = 0;
    public static final int BLACK_SPECIAL_ROW = 7;
    public static final int START_INDEX = 0;
    public static final int MAX_INDEX = 8;
    private List<Rank> ranks;

    public Board() {
        initialize();
    }

    public void initialize() {
        initializeEmpty();
        initializeSpecialPiece();
        initializePawn();
    }

    public void initializeEmpty() {
        ranks = new ArrayList<>();
        IntStream.range(START_INDEX, MAX_INDEX)
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

    public String getBoard() {
        StringBuilder stringBuilder = new StringBuilder();

        /* 마지막 행부터 출력하여 출력 순서를 맞춰준다. */
        for (int rank = MAX_INDEX - 1; rank >= START_INDEX; rank--) {
            stringBuilder.append(appendNewLine(ranks.get(rank).toString()));
        }
        return stringBuilder.toString();
    }

    public void put(Piece piece, Position position) {
        ranks.get(position.getRank()).putPiece(piece, position.getFile());
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getRank())
                .getPiece(position.getFile());
    }

    public int pieceAllCount() {
        return IntStream.range(START_INDEX, MAX_INDEX)
                .map(this::pieceFileCount)
                .sum();
    }

    public int pieceCount(Piece piece) {
        return IntStream.range(START_INDEX, MAX_INDEX)
                .map(file -> pieceFileCount(piece, file))
                .sum();
    }

    public int pieceCount(Color color) {
        return IntStream.range(START_INDEX, MAX_INDEX)
                .map(file -> pieceFileCountByColor(color, file))
                .sum();
    }

    public int pieceFileCount(Piece piece, int file) {
        return (int) ranks.stream()
                .filter(rank -> rank.getPiece(file).equals(piece))
                .count();
    }

    private int pieceFileCountByColor(Color color, int file) {
        return (int) ranks.stream()
                .filter(rank -> rank.getPiece(file).getColor().equals(color))
                .count();
    }

    private int pieceFileCount(int file) {
        return (int) ranks.stream()
                .filter(rank -> !rank.getPiece(file).getType().equals(Type.NO_PIECE))
                .count();
    }
}