package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.position.Position;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Queen 기물 테스트")
class QueenTest {
    private Piece queen;
    @BeforeEach
    void setUp() {
        queen = Piece.create(Type.QUEEN, Color.WHITE);
    }

    @Test
    @DisplayName("Queen 기물의 이동을 검증한다.")
    void verifyAllow() throws Exception{
        Position startPosition = new Position("c3");

        Position northPosition = new Position("c8");
        Position eastPosition = new Position("f3");
        Position westPosition = new Position("a3");
        Position southPosition = new Position("c1");
        Position northWestPosition = new Position("a5");
        Position northEastPosition = new Position("f6");
        Position southEastPosition = new Position("e1");
        Position southWestPosition = new Position("b2");

        assertAll(
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, northPosition)),
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, eastPosition)),
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, westPosition)),
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, southPosition)),
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, northWestPosition)),
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, northEastPosition)),
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, southEastPosition)),
                () -> assertDoesNotThrow(() -> queen.verifyMovePosition(startPosition, southWestPosition))
        );
    }

    @Test
    @DisplayName("Queen 기물의 올바르지 않은 이동에는 예외가 발생한다.")
    void verifyFail() throws Exception{
        Position startPosition = new Position("c3");

        Position impossiblePosition1 = new Position("c3");
        Position impossiblePosition2 = new Position("d8");
        Position impossiblePosition3 = new Position("f1");
        Position impossiblePosition4 = new Position("a2");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> queen.verifyMovePosition(startPosition, impossiblePosition1)),
                () -> assertThrows(IllegalArgumentException.class, () -> queen.verifyMovePosition(startPosition, impossiblePosition2)),
                () -> assertThrows(IllegalArgumentException.class, () -> queen.verifyMovePosition(startPosition, impossiblePosition3)),
                () -> assertThrows(IllegalArgumentException.class, () -> queen.verifyMovePosition(startPosition, impossiblePosition4))
        );
    }
}
