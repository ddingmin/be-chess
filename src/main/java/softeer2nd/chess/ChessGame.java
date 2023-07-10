package softeer2nd.chess;

import softeer2nd.chess.board.Board;
import softeer2nd.chess.board.Position;
import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.Command.*;

public class ChessGame {
    private Board board;
    private boolean isRunning;

    public ChessGame(Board board) {
        this.board = board;
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
                    move(new Position(inputs[1]), new Position(inputs[2]));
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
    public void move(Position sourcePosition, Position targetPosition) {
        board.put(board.findPiece(sourcePosition), targetPosition);
        board.put(Piece.createBlank(), sourcePosition);
    }

    public void initialize() {
        board.initialize();
    }
}
