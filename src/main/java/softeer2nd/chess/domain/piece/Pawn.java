package softeer2nd.chess.domain.piece;

public class Pawn {
    private String color;
    public Pawn(String color) {
        this.color = color;
    }
    public Pawn() { this.color = "white"; }

    public String getColor() {
        return color;
    }
}
