package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.utils.Position;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pawn 기물 테스트")
class PawnTest {
    private Piece whitePawn;
    private Piece blackPawn;
    @BeforeEach
    void setUp() {
        whitePawn = Piece.create(Type.PAWN, Color.WHITE);
        blackPawn = Piece.create(Type.PAWN, Color.BLACK);
    }

    @Test
    @DisplayName("White Pawn 기물의 이동을 검증한다.")
    void verifyAllowWhite() throws Exception{
        Position startPosition = new Position("c3");

        Position northPosition = new Position("c4");
        Position northWestPosition = new Position("b4");
        Position northEastPosition = new Position("d4");

        assertAll(
                () -> assertDoesNotThrow(() -> whitePawn.verifyMovePosition(startPosition, northPosition)),
                () -> assertDoesNotThrow(() -> whitePawn.verifyMovePosition(startPosition, northWestPosition)),
                () -> assertDoesNotThrow(() -> whitePawn.verifyMovePosition(startPosition, northEastPosition))
        );
    }

    @Test
    @DisplayName("Black Pawn 기물의 이동을 검증한다.")
    void verifyAllowBlack() throws Exception{
        Position startPosition = new Position("c3");

        Position southPosition = new Position("c2");
        Position southEastPosition = new Position("d2");
        Position southWestPosition = new Position("b2");

        assertAll(
                () -> assertDoesNotThrow(() -> blackPawn.verifyMovePosition(startPosition, southPosition)),
                () -> assertDoesNotThrow(() -> blackPawn.verifyMovePosition(startPosition, southEastPosition)),
                () -> assertDoesNotThrow(() -> blackPawn.verifyMovePosition(startPosition, southWestPosition))
        );
    }

    @Test
    @DisplayName("White Pawn 기물의 올바르지 않은 이동에는 예외가 발생한다.")
    void verifyFailWhite() throws Exception{
        Position startPosition = new Position("c3");

        Position impossiblePosition1 = new Position("c3");
        Position impossiblePosition2 = new Position("c5");
        Position impossiblePosition3 = new Position("c2");
        Position impossiblePosition4 = new Position("b1");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> whitePawn.verifyMovePosition(startPosition, impossiblePosition1)),
                () -> assertThrows(IllegalArgumentException.class, () -> whitePawn.verifyMovePosition(startPosition, impossiblePosition2)),
                () -> assertThrows(IllegalArgumentException.class, () -> whitePawn.verifyMovePosition(startPosition, impossiblePosition3)),
                () -> assertThrows(IllegalArgumentException.class, () -> whitePawn.verifyMovePosition(startPosition, impossiblePosition4))
        );
    }

    @Test
    @DisplayName("Black Pawn 기물의 올바르지 않은 이동에는 예외가 발생한다.")
    void verifyFailBlack() throws Exception{
        Position startPosition = new Position("c3");

        Position impossiblePosition1 = new Position("c3");
        Position impossiblePosition2 = new Position("c1");
        Position impossiblePosition3 = new Position("c4");
        Position impossiblePosition4 = new Position("d4");

        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> blackPawn.verifyMovePosition(startPosition, impossiblePosition1)),
                () -> assertThrows(IllegalArgumentException.class, () -> blackPawn.verifyMovePosition(startPosition, impossiblePosition2)),
                () -> assertThrows(IllegalArgumentException.class, () -> blackPawn.verifyMovePosition(startPosition, impossiblePosition3)),
                () -> assertThrows(IllegalArgumentException.class, () -> blackPawn.verifyMovePosition(startPosition, impossiblePosition4))
        );
    }
}
