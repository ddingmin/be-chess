package softeer2nd;

import org.junit.jupiter.api.*;
import softeer2nd.chess.domain.piece.Pawn;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {
    final String WHITE = "white";
    final String BLACK = "black";
    final ArrayList<String> validColors = new ArrayList<>(List.of("white", "black"));

    @Test
    @DisplayName("입력한 색의 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(WHITE);
        verifyPawn(BLACK);
    }

    @Test
    @DisplayName("옳바른 색이 입력되어야 한다.")
    public void createCorrectColor(){
        verifyColor(WHITE);
        verifyColor(BLACK);
        verifyColor("yellow");
    }

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    private void verifyColor(final String color) {
        assertThat(color).isIn(validColors);
    }
}