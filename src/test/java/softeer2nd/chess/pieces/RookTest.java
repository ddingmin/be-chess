package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.position.Position;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Rook 기물 테스트")
class RookTest {
    private Piece rook;
    @BeforeEach
    void setUp() {
        rook = Piece.create(Type.ROOK, Color.WHITE);
    }

    @Test
    @DisplayName("Rook 기물의 이동을 검증한다.")
    void verifyAllow() throws Exception{
        Position startPosition = new Position("c3");

        Position northPosition = new Position("c8");
        Position eastPosition = new Position("f3");
        Position westPosition = new Position("a3");
        Position southPosition = new Position("c1");

        assertAll(
                () -> assertDoesNotThrow(() -> rook.verifyMovePosition(startPosition, northPosition)),
                () -> assertDoesNotThrow(() -> rook.verifyMovePosition(startPosition, eastPosition)),
                () -> assertDoesNotThrow(() -> rook.verifyMovePosition(startPosition, westPosition)),
                () -> assertDoesNotThrow(() -> rook.verifyMovePosition(startPosition, southPosition))
        );
    }

    @Test
    @DisplayName("Rook 기물의 올바르지 않은 이동에는 예외가 발생한다.")
    void verifyFail() throws Exception{
        Position startPosition = new Position("c3");

        Position impossiblePosition1 = new Position("c3");
        Position impossiblePosition2 = new Position("d8");
        Position impossiblePosition3 = new Position("f1");
        Position impossiblePosition4 = new Position("b4");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> rook.verifyMovePosition(startPosition, impossiblePosition1)),
                () -> assertThrows(IllegalArgumentException.class, () -> rook.verifyMovePosition(startPosition, impossiblePosition2)),
                () -> assertThrows(IllegalArgumentException.class, () -> rook.verifyMovePosition(startPosition, impossiblePosition3)),
                () -> assertThrows(IllegalArgumentException.class, () -> rook.verifyMovePosition(startPosition, impossiblePosition4))
        );
    }
}
