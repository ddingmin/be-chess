package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;

import static softeer2nd.chess.Command.*;

public class GameController {
    private final Board board;
    private boolean isRunning;

    public GameController() {
        board = new Board();
    }

    public void main() {
        isRunning = true;
        String command;
        while (isRunning) {
            command = readRun();
            if (command.equals(START)) {
                startGame();
                endGame();
            }
            if (command.equals(END)) {
                exitGame();
            }
        }
    }

    private void startGame() {
        boolean isGaming = true;
        showStartGameMessage();
        board.initialize();
        showGameBoard(board);

        String command;
        while (isGaming) {
            command = readGame();
            if (command.equals(END)) {
                isGaming = false;
            }
            if (command.startsWith(MOVE)) {
                String[] inputs = command.split(" ");
                try {
                    board.move(new Position(inputs[1]), new Position(inputs[2]));
                    showGameBoard(board);
                }
                catch (IllegalArgumentException exception) {
                    System.out.println(POSITION_INPUT_ERROR);
                }
            }
        }
    }

    private void endGame() {
        showEndGameMessage();
    }

    private void exitGame() {
        showExitGameMessage();
        isRunning = false;
    }
}
