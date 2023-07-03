package softeer2nd;

import org.junit.jupiter.api.*;
import softeer2nd.chess.domain.piece.Pawn;

import static org.assertj.core.api.Assertions.*;

public class PawnTest {

    @Test
    @DisplayName("입력한 색의 폰이 생성되어야 한다")
    public void create() {
        Pawn pawnWhite = new Pawn("white");
        assertThat(pawnWhite.getColor()).isEqualTo("white");
        Pawn pawnBlack = new Pawn("black");
        assertThat(pawnBlack.getColor()).isEqualTo("black");
    }
}