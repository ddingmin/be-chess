package softeer2nd.chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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

    private Position create(String string) {
        return new Position(string);
    }
}