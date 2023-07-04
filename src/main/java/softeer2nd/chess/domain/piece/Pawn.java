package softeer2nd.chess.domain.piece;

public class Pawn {
    static final String WHITE_COLOR = "white";
    static final String BLACK_COLOR = "black";


    private final String color;

    public Pawn(String color) {
        this.color = color;
    }

    public Pawn() {
        this.color = WHITE_COLOR;
    }

    public String getColor() {
        return color;
    }
}
