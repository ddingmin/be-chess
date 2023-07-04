package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<Pawn> board;

    public Board() {
        board = new ArrayList<>();
    }

    public void add(Pawn pawn) {
        this.board.add(pawn);
    }

    public int size() {
        return board.size();
    }

    public Pawn findPawn(int index) {
        return board.get(index);
    }
}
