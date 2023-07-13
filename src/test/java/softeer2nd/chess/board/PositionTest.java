package softeer2nd.chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.position.Position;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("좌표 클래스 테스트")
class PositionTest {
    @Test
    @DisplayName("주어진 좌표가 생성된다.")
    void create() {
        // given
        String a1 = "a1";
        String a8 = "a8";
        String h1 = "h1";
        String h8 = "h8";

        // then
        assertDoesNotThrow(() -> create(a1));
        assertDoesNotThrow(() -> create(a8));
        assertDoesNotThrow(() -> create(h1));
        assertDoesNotThrow(() -> create(h8));
    }

    @Test
    @DisplayName("잘못된 좌표에 예외가 발생한다.")
    void createFail() {
        // given
        String reverseString = "1a";
        String startString = "start";
        String nullString = "";
        String overString1 = "a9";
        String overString2 = "z2";

        // then
        assertThrows(IllegalArgumentException.class, () -> create(reverseString));
        assertThrows(IllegalArgumentException.class, () -> create(startString));
        assertThrows(IllegalArgumentException.class, () -> create(nullString));
        assertThrows(IllegalArgumentException.class, () -> create(overString1));
        assertThrows(IllegalArgumentException.class, () -> create(overString2));
    }

    private Position create(String string) {
        return new Position(string);
    }
}
