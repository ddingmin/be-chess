package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.position.Position;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("King 기물 테스트")
class KingTest {
    private Piece king;

    @BeforeEach
    void setUp() {
        king = Piece.create(Type.KING, Color.WHITE);
    }

    @Test
    @DisplayName("King 기물의 이동을 검증한다.")
    void verifyAllow() throws Exception {
        Position startPosition = new Position("c3");

        Position northPosition = new Position("c4");
        Position eastPosition = new Position("d3");
        Position westPosition = new Position("b3");
        Position southPosition = new Position("c2");
        Position northWestPosition = new Position("b4");
        Position northEastPosition = new Position("d4");
        Position southEastPosition = new Position("d2");
        Position southWestPosition = new Position("b2");

        assertAll(
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, northPosition)),
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, eastPosition)),
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, westPosition)),
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, southPosition)),
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, northWestPosition)),
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, northEastPosition)),
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, southEastPosition)),
                () -> assertDoesNotThrow(() -> king.verifyMovePosition(startPosition, southWestPosition))
        );
    }

    @Test
    @DisplayName("King 기물의 올바르지 않은 이동에는 예외가 발생한다.")
    void verifyFail() throws Exception {
        Position startPosition = new Position("c3");

        Position impossiblePosition1 = new Position("c3");
        Position impossiblePosition2 = new Position("d8");
        Position impossiblePosition3 = new Position("f1");
        Position impossiblePosition4 = new Position("a1");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> king.verifyMovePosition(startPosition, impossiblePosition1)),
                () -> assertThrows(IllegalArgumentException.class, () -> king.verifyMovePosition(startPosition, impossiblePosition2)),
                () -> assertThrows(IllegalArgumentException.class, () -> king.verifyMovePosition(startPosition, impossiblePosition3)),
                () -> assertThrows(IllegalArgumentException.class, () -> king.verifyMovePosition(startPosition, impossiblePosition4))
        );
    }
}
