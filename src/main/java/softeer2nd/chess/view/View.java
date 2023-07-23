package softeer2nd.chess.view;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.pieces.Color;

public class View {
    private static View view = new View();

    private View() {
    }

    public static View getView() {
        return view;
    }

    public void printBoard(Board board) {
        System.out.println("== 체스판 ==");
        System.out.println(board.getBoard());
    }

    public void printProgramCommand() {
        System.out.println("다음 입력을 사용해 주세요. (시작: start, 종료: end)");
    }

    public void printProgramEnd() {
        System.out.println("프로그램을 종료합니다.");
    }

    public void printError() {
        System.out.println("잘못된 입력입니다. 올바른 명령어를 입력해주세요.");
    }

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void printGameCommand() {
        System.out.println("이동 명령어와 이동시킬 좌표, 이동할 좌표를 입력해주세요. (이동: move a2 a3, 체스판: show, 종료: end)");
    }

    public void printGameEnd() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public void printWinner(Color winner) {
        System.out.println(winner + " 승리!");
    }
}
