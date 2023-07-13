package softeer2nd;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.utils.Position;
import softeer2nd.chess.view.View;

import java.util.Scanner;

public class Main {
    public static final String START = "start";
    public static final String END = "end";
    private static final String SHOW = "show";
    public static final String MOVE = "move ";
    public static final View view = View.getView();
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);

        runProgram(board, chessGame);
    }

    private static void runProgram(Board board, ChessGame chessGame) {
        boolean isRunning = true;
        String command;

        while (isRunning) {
            view.printProgramCommand();
            command = scanner.nextLine();

            if (!isCorrectProgramCommand(command)) {
                view.printError();
                continue;
            }

            if (command.equals(END)) {
                view.printProgramEnd();
                isRunning = false;
            }
            if (command.equals(START)) {
                chessGame.initialize();
                view.printBoard(board);

                runGame(board, chessGame);
            }
        }
    }

    private static void runGame(Board board, ChessGame chessGame) {
        boolean isGaming = true;
        String command;

        while (isGaming) {
            view.printGameCommand();
            command = scanner.nextLine();

            if (!isCorrectGameCommand(command)) {
                view.printError();
                continue;
            }
            if (command.equals(END)) {
                view.printGameEnd();
                isGaming = false;
                continue;
            }
            if (command.equals(SHOW)) {
                view.printBoard(board);
                continue;
            }
            if (command.startsWith(MOVE)) {
                view.printGameCommand();
                move(board, chessGame, command);
            }
        }
    }

    private static void move(Board board, ChessGame chessGame, String command) {
        String[] moves = command.split(" ");

        if (!isCorrectMoveCommand(moves)) {
            view.printError();
            return;
        }

        try {
            chessGame.move(new Position(moves[1]), new Position(moves[2]));
        } catch (RuntimeException runtimeException) {
            view.printError(runtimeException.getMessage());
            return;
        }

        view.printBoard(board);
    }

    private static boolean isCorrectGameCommand(String command) {
        return command.equals(END) || command.startsWith(MOVE) || command.equals(SHOW);
    }

    private static boolean isCorrectMoveCommand(String[] commands) {
        return commands.length == 3;
    }

    private static boolean isCorrectProgramCommand(String command) {
        return command.equals(START) || command.equals(END);
    }
}
