package softeer2nd.chess.view;

import softeer2nd.chess.board.Board;

public class View {
    private final Board board;

    public View(Board board) {
        this.board = board;
    }

    public void printBoard() {
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

    public void printGameCommand() {
        System.out.println("이동 명령어와 이동시킬 좌표, 이동할 좌표를 입력해주세요. (이동: move a2 a3, 종료: end)");
    }

    public void printGameEnd() {
        System.out.println("체스 게임을 종료합니다.");
    }
}
