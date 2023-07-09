package softeer2nd.chess;

import softeer2nd.chess.board.Board;

import java.util.Scanner;

public class Command {
    private static final Scanner scanner = new Scanner(System.in);
    public static final String START_COMMAND_MESSAGE = "다음 입력을 사용해 주세요. (시작: start, 종료: end)";
    public static final String GAME_COMMAND_ERROR = "잘못된 입력입니다. 다시 입력해주세요. (시작: start, 종료: end)";
    public static final String GAME_MOVE_INPUT = "이동 명령어와 이동시킬 좌표, 이동할 좌표를 입력해주세요. (이동: move a2 a3, 종료: end)";
    public static final String GAME_START_MESSAGE = "체스 게임을 시작합니다.";
    public static final String GAME_END_MESSAGE = "체스 게임을 종료합니다.";
    public static final String GAME_EXIT_MESSAGE = "체스 게임 프로그램을 종료합니다.";
    public static final String POSITION_INPUT_ERROR = "올바른 좌표 입력이 아닙니다.";
    public static final String SHOW_BOARD_MESSAGE = "== 체스판 ==";
    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";


    public Command() {
    }

    public static String readRun() {
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

    public static String readGame() {
        System.out.println(GAME_MOVE_INPUT);
        String input = scanner.nextLine();
        while (!isValidGameInput(input)) {
            System.out.println(POSITION_INPUT_ERROR);
            input = scanner.nextLine();
        }
        return input;
    }

    private static boolean isValidGameInput(String input) {
        if (input.startsWith(END) && isCorrectEndCommand(input)) {
            return true;
        }
        if (input.startsWith(MOVE) && isCorrectMoveCommand(input)) {
            return true;
        }
        return false;
    }

    private static boolean isCorrectEndCommand(String input) {
        return input.equals(END);
    }

    private static boolean isCorrectMoveCommand(String input) {
        String[] inputs = input.split(" ");
        return inputs.length == 3 && (inputs[1].length() == 2 || inputs[2].length() == 2);
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
