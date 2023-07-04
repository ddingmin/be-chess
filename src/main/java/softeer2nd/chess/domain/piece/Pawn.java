package softeer2nd.chess.domain.piece;

public class Pawn {
    static final String WHITE = "white";
    static final String BLACK = "black";


    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = WHITE;
    }

    public String getColor() {
        return color;
    }
}
