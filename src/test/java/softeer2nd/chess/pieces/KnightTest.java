package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.board.Position;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Knight 기물 테스트")
class KnightTest {
    private Piece knight;
    @BeforeEach
    void setUp() {
        knight = Piece.create(Type.KNIGHT, Color.WHITE);
    }

    @Test
    @DisplayName("Knight 기물의 이동을 검증한다.")
    void verifyAllow() throws Exception{
        Position startPosition = new Position("c3");

        Position nnwPosition = new Position("b5");
        Position nnetPosition = new Position("d5");
        Position wwnPosition = new Position("a4");
        Position wwsPosition = new Position("a2");
        Position eenPosition = new Position("e4");
        Position eesPosition = new Position("e2");
        Position sswPosition = new Position("b1");
        Position ssePosition = new Position("d1");

        assertAll(
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, nnwPosition)),
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, nnetPosition)),
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, wwnPosition)),
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, wwsPosition)),
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, eenPosition)),
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, eesPosition)),
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, sswPosition)),
                () -> assertDoesNotThrow(() -> knight.verifyMovePosition(startPosition, ssePosition))
        );
    }

    @Test
    @DisplayName("Knight 기물의 올바르지 않은 이동에는 예외가 발생한다.")
    void verifyFail() throws Exception{
        Position startPosition = new Position("c3");

        Position impossiblePosition1 = new Position("c3");
        Position impossiblePosition2 = new Position("d8");
        Position impossiblePosition3 = new Position("f1");
        Position impossiblePosition4 = new Position("a1");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> knight.verifyMovePosition(startPosition, impossiblePosition1)),
                () -> assertThrows(IllegalArgumentException.class, () -> knight.verifyMovePosition(startPosition, impossiblePosition2)),
                () -> assertThrows(IllegalArgumentException.class, () -> knight.verifyMovePosition(startPosition, impossiblePosition3)),
                () -> assertThrows(IllegalArgumentException.class, () -> knight.verifyMovePosition(startPosition, impossiblePosition4))
        );
    }
}
