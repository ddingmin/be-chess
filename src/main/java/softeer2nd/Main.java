package softeer2nd;

import softeer2nd.chess.ChessGame;
import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;
import softeer2nd.chess.view.View;

import java.util.Scanner;

public class Main {
    public static final String START = "start";
    public static final String END = "end";
    public static final String MOVE = "move";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        View view = new View(board);
        String command;

        boolean isRunning = true;
        boolean isGaming;

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
                isGaming = true;
                chessGame.initialize();
                view.printBoard();

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
                    }
                    if (command.startsWith(MOVE)) {
                        view.printGameCommand();
                        String[] moves = command.split(" ");
                        chessGame.move(new Position(moves[1]), new Position(moves[2]));
                        view.printBoard();
                    }
                }
            }
        }
    }

    private static boolean isCorrectGameCommand(String command) {
        return command.equals(END) || command.startsWith(MOVE);
    }

    private static boolean isCorrectProgramCommand(String command) {
        return command.equals(START) || command.equals(END);
    }
}