package softeer2nd.chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("체스 Rank 테스트")
class RankTest {

    @Test
    @DisplayName("빈 rank를 생성한다.")
    void createEmpty() {
        Rank rankEmpty = Rank.createEmpty();

        assertEquals("........", rankEmpty.toString());
    }

    @Test
    @DisplayName("흰색 폰들이 존재하는 rank를 생성한다.")
    void createWhitePawns() {
        Rank rankWhitePawns = Rank.createWhitePawns();

        assertEquals("pppppppp", rankWhitePawns.toString());
    }

    @Test
    @DisplayName("검정색 폰들이 존재하는 rank를 생성한다.")
    void createBlackPawns() {
        Rank rankBlackPawns = Rank.createBlackPawns();

        assertEquals("PPPPPPPP", rankBlackPawns.toString());
    }

    @Test
    @DisplayName("흰색 폰들이 존재하는 rank를 생성한다.")
    void createWhiteSpecialPieces() {
        Rank rankWhiteSpecialPieces = Rank.createWhiteSpecialPieces();

        assertEquals("rnbqkbnr", rankWhiteSpecialPieces.toString());
    }

    @Test
    @DisplayName("검정색 특별 기물들이 존재하는 rank를 생성한다.")
    void createBlackSpecialPieces() {
        Rank rankBlackSpecialPieces = Rank.createBlackSpecialPieces();

        assertEquals("RNBQKBNR", rankBlackSpecialPieces.toString());
    }
}