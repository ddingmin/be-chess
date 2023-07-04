package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("좌표 테스트")
class PointTest {
    @Test
    @DisplayName("좌표 생성 테스트")
    void create() {
        verifyPoint(2, 1);
        verifyPoint(1, 8);
    }

    void verifyPoint(int x, int y) {
        assertThat(x).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(8);
        assertThat(y).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(8);
        Point point = new Point(x, y);
        assertThat(point.getX()).isEqualTo(x);
        assertThat(point.getY()).isEqualTo(y);
    }
}
