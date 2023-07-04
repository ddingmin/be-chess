package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PawnTest {
    @Test
    @DisplayName("기본 생성자로 생성한 경우 white 폰이 생성되어야 한다")
    public void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Pawn.WHITE_COLOR, pawn.getColor());
    }

    @Test
    @DisplayName("입력한 색의 폰이 생성되어야 한다")
    public void create() {
        verifyPawn(Pawn.WHITE_COLOR);
        verifyPawn(Pawn.BLACK_COLOR);
    }


    private void verifyPawn(final String color) {
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }
}