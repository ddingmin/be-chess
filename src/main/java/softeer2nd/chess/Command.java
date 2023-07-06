package softeer2nd.chess;

import java.util.Scanner;

public class Command {
    public static final String START_COMMAND_MESSAGE = "다음 입력을 사용해 주세요. (시작: start, 종료 end)";
    public static final String GAME_COMMAND_ERROR = "잘못된 입력입니다. 다시 입력해주세요. (시작: start, 종료 end)";
    public static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    public static final String GAME_END_MESSAGE = "체스 게임을 종료합니다.";
    public static final String GAME_EXIT_MESSAGE = "체스 게임 프로그램을 종료합니다.";
    public static final String SHOW_BOARD_MESSAGE = "== 진행 상황 ==";
    public static final String START = "start";
    public static final String END = "end";

    public Command() {
    }

    public static String readRun() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(START_COMMAND_MESSAGE);
        String input = scanner.nextLine();
        while (!isValidRunInput(input)) {
            System.out.println(GAME_COMMAND_ERROR);
            input = scanner.nextLine();
        }
        return input;
    }

    private static boolean isValidRunInput(String input) {
        if (input == null) {
            return false;
        }
        if (input.equals(START) || input.equals(END)) {
            return true;
        }
        return false;
    }

    public static void showStartGameMessage() {
        System.out.println(GAME_START_MESSAGE);
    }

    public static void showGameBoard(Board board) {
        System.out.println(SHOW_BOARD_MESSAGE);
        System.out.println(board.showBoard());
    }

    public static void showEndGameMessage() {
        System.out.println(GAME_END_MESSAGE);
    }

    public static void showExitGameMessage() {
        System.out.println(GAME_EXIT_MESSAGE);
    }
}
