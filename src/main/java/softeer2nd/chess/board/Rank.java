package softeer2nd.chess.board;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class Rank {
    private static final int RANK_SIZE = 8;
    private List<Piece> rank;

    private Rank(List<Piece> pieces) {
        this.rank = pieces;
    }

    public static Rank createEmpty() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < RANK_SIZE; i++) {
            pieces.add(Piece.createBlank());
        }
        return new Rank(pieces);
    }

    public static Rank createWhitePawn() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < RANK_SIZE; i++) {
            pieces.add(Piece.createWhitePawn());
        }
        return new Rank(pieces);
    }

    public static Rank createBlackPawn() {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < RANK_SIZE; i++) {
            pieces.add(Piece.createBlackPawn());
        }
        return new Rank(pieces);
    }

    public static Rank createWhiteSpecialPiece() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(Piece.createWhiteRook());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteQueen());
        pieces.add(Piece.createWhiteKing());
        pieces.add(Piece.createWhiteBishop());
        pieces.add(Piece.createWhiteKnight());
        pieces.add(Piece.createWhiteRook());
        return new Rank(pieces);
    }

    public static Rank createBlackSpecialPiece() {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(Piece.createBlackRook());
        pieces.add(Piece.createBlackKnight());
        pieces.add(Piece.createBlackBishop());
        pieces.add(Piece.createBlackQueen());
        pieces.add(Piece.createBlackKing());
        pieces.add(Piece.createBlackBishop());
        pieces.add(Piece.createBlackKnight());
        pieces.add(Piece.createBlackRook());
        return new Rank(pieces);
    }

    public Piece getPiece(int file) {
        return rank.get(file);
    }

    public void putPiece(Piece piece, int file) {
        rank.set(file, piece);
    }
}
