package softeer2nd.chess;

import java.util.Scanner;

public class GameController {
    public static final String START_COMMAND_MESSAGE = "다음 커맨드를 사용해 주세요. (시작: start, 종료 end)";
    public static final String GAME_COMMAND_ERROR = "잘못된 입력입니다. 다시 입력해주세요.";
    public static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    public static final String GAME_END_MESSAGE = "체스 게임을 종료합니다.";
    public static final String GAME_EXIT_MESSAGE = "체스 게임 프로그램을 종료합니다.";
    public static final String SHOW_BOARD_MESSAGE = "== 진행 상황 ==";
    public static final String START = "START";
    public static final String END = "END";
    private Board board;
    private String command;
    private boolean isGaming;

    Scanner sc = new Scanner(System.in);

    public GameController() {
        board = new Board();
    }

    public void main() {
        isGaming = true;

        while (isGaming) {
            System.out.println(START_COMMAND_MESSAGE);
            command = sc.nextLine();
            while (!isValidCommand(command)) {
                System.out.println(GAME_COMMAND_ERROR);
                command = sc.nextLine();
            }
            if (command.equalsIgnoreCase(START)) {
                startGame();
                System.out.println(GAME_END_MESSAGE);
            }
            if (command.equalsIgnoreCase(END)) {
                System.out.println(GAME_EXIT_MESSAGE);
                break;
            }
        }
    }

    private void startGame() {
        System.out.println(GAME_START_MESSAGE);
        board.initialize();
        System.out.println(SHOW_BOARD_MESSAGE);
        System.out.println(board.getBoardResult());
        //TODO: 게임 로직을 추가로 구현해야 한다.
    }

    private boolean isValidCommand(String command) {
        if (command == null) {
            return false;
        }
        for (int i = 0; i < command.length(); i++) {
            if (!Character.isAlphabetic(command.charAt(i))) {
                return false;
            }
        }
        if (command.equalsIgnoreCase(START) || command.equalsIgnoreCase(END)) {
            return true;
        }
        return false;
    }
}
