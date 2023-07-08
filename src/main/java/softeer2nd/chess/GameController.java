package softeer2nd.chess;

import softeer2nd.chess.board.Board;

import static softeer2nd.chess.Command.*;

public class GameController {
    private Board board;
    private boolean isGaming;

    public GameController() {
        board = new Board();
    }

    public void main() {
        isGaming = true;
        String command;
        while (isGaming) {
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
        showStartGameMessage();
        board.initialize();
        showGameBoard(board);
        //TODO: 게임 로직을 추가로 구현해야 한다.
    }

    private void endGame() {
        showEndGameMessage();
    }

    private void exitGame() {
        showExitGameMessage();
        isGaming = false;
    }
}
