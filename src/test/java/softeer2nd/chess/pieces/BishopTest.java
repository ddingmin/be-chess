package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.position.Position;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bishop 기물 테스트")
class BishopTest {
    private Piece bishop;

    @BeforeEach
    void setUp() {
        bishop = Piece.create(Type.BISHOP, Color.WHITE);
    }

    @Test
    @DisplayName("Bishop 기물의 이동을 검증한다.")
    void verifyAllow() throws Exception {
        Position startPosition = new Position("c3");

        Position northWestPosition = new Position("a5");
        Position northEastPosition = new Position("f6");
        Position southEastPosition = new Position("e1");
        Position southWestPosition = new Position("b2");

        assertAll(
                () -> assertDoesNotThrow(() -> bishop.verifyMovePosition(startPosition, northWestPosition)),
                () -> assertDoesNotThrow(() -> bishop.verifyMovePosition(startPosition, northEastPosition)),
                () -> assertDoesNotThrow(() -> bishop.verifyMovePosition(startPosition, southEastPosition)),
                () -> assertDoesNotThrow(() -> bishop.verifyMovePosition(startPosition, southWestPosition))
        );
    }

    @Test
    @DisplayName("Bishop 기물의 올바르지 않은 이동에는 예외가 발생한다.")
    void verifyFail() throws Exception {
        Position startPosition = new Position("c3");

        Position impossiblePosition1 = new Position("c3");
        Position impossiblePosition2 = new Position("d8");
        Position impossiblePosition3 = new Position("f1");
        Position impossiblePosition4 = new Position("c4");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> bishop.verifyMovePosition(startPosition, impossiblePosition1)),
                () -> assertThrows(IllegalArgumentException.class, () -> bishop.verifyMovePosition(startPosition, impossiblePosition2)),
                () -> assertThrows(IllegalArgumentException.class, () -> bishop.verifyMovePosition(startPosition, impossiblePosition3)),
                () -> assertThrows(IllegalArgumentException.class, () -> bishop.verifyMovePosition(startPosition, impossiblePosition4))
        );
    }
}
