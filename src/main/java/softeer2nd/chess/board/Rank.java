package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Color;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Rank {
    private static final int RANK_SIZE = 8;
    private final List<Piece> rank;

    private Rank(List<Piece> pieces) {
        this.rank = pieces;
    }

    public static Rank createEmpty() {
        List<Piece> pieces = IntStream.range(0, RANK_SIZE).mapToObj(i -> Piece.createBlank()).collect(Collectors.toList());
        return new Rank(pieces);
    }

    public static Rank createWhitePawns() {
        List<Piece> pieces = IntStream.range(0, RANK_SIZE).mapToObj(i -> Piece.create(Type.PAWN, Color.WHITE)).collect(Collectors.toList());
        return new Rank(pieces);
    }

    public static Rank createBlackPawns() {
        List<Piece> pieces = IntStream.range(0, RANK_SIZE).mapToObj(i -> Piece.create(Type.PAWN, Color.BLACK)).collect(Collectors.toList());
        return new Rank(pieces);
    }

    public static Rank createWhiteSpecialPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(Piece.create(Type.ROOK, Color.WHITE));
        pieces.add(Piece.create(Type.KNIGHT, Color.WHITE));
        pieces.add(Piece.create(Type.BISHOP, Color.WHITE));
        pieces.add(Piece.create(Type.QUEEN, Color.WHITE));
        pieces.add(Piece.create(Type.KING, Color.WHITE));
        pieces.add(Piece.create(Type.BISHOP, Color.WHITE));
        pieces.add(Piece.create(Type.KNIGHT, Color.WHITE));
        pieces.add(Piece.create(Type.ROOK, Color.WHITE));
        return new Rank(pieces);
    }

    public static Rank createBlackSpecialPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(Piece.create(Type.ROOK, Color.BLACK));
        pieces.add(Piece.create(Type.KNIGHT, Color.BLACK));
        pieces.add(Piece.create(Type.BISHOP, Color.BLACK));
        pieces.add(Piece.create(Type.QUEEN, Color.BLACK));
        pieces.add(Piece.create(Type.KING, Color.BLACK));
        pieces.add(Piece.create(Type.BISHOP, Color.BLACK));
        pieces.add(Piece.create(Type.KNIGHT, Color.BLACK));
        pieces.add(Piece.create(Type.ROOK, Color.BLACK));
        return new Rank(pieces);
    }

    public Piece getPiece(int file) {
        return rank.get(file);
    }

    public void putPiece(Piece piece, int file) {
        rank.set(file, piece);
    }

    @Override
    public String toString() {
        return rank.stream()
                .map(piece -> Character.toString(piece.getRepresentation()))
                .reduce("", (str1, str2) -> str1 + str2);
    }
}
