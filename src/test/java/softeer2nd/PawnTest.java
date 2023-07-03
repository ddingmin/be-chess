package softeer2nd;

import org.junit.jupiter.api.*;
import softeer2nd.chess.domain.piece.Pawn;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    @Test
    @DisplayName("입력한 색의 폰이 생성되어야 한다")
    public void create() {
        final String WHITE = "white";
        final String BLACK = "black";

        verifyPawn(WHITE);
        verifyPawn(BLACK);
    }

    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}