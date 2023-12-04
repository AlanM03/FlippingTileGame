package org.fliptile.model;

public class GameManager {
    private GameBoard gameBoard;
    private final Player currentPlayer;
    private boolean isGameInProgress;
    private int moveCounter;

    public GameManager(String playerName) {
        this.currentPlayer = new Player(playerName);
        this.isGameInProgress = false;
        this.moveCounter = 0;
    }

    public void startGame(int rows, int columns) {
        this.gameBoard = new GameBoard(rows, columns);
        currentPlayer.updateScore(0);
        isGameInProgress = true;
        moveCounter = 0;
    }

    public boolean processMove(int row1, int col1, int row2, int col2) {
        if (!isGameInProgress) {
            return false; // Game not in progress
        }

        if (!isValidMove(row1, col1, row2, col2)) {
            throw new IllegalArgumentException("Invalid tile coordinates.");
        }

        moveCounter++;

        Tile firstTile = gameBoard.getBoard()[row1][col1];
        Tile secondTile = gameBoard.getBoard()[row2][col2];

        firstTile.flip();
        secondTile.flip();

        if (gameBoard.checkMatch(firstTile, secondTile)) {
            currentPlayer.updateScore(1);
            if (currentPlayer.getScore() == gameBoard.getTotalPairs()) {
                isGameInProgress = false; // End game if maximum score is reached
            }
        } else {
            firstTile.flip(); // Flip back if not a match
            secondTile.flip();
        }

        return isGameInProgress;
    }

    private boolean isValidMove(int row1, int col1, int row2, int col2) {
        return row1 >= 0 && row1 < gameBoard.getRows() && col1 >= 0 && col1 < gameBoard.getColumns() &&
                row2 >= 0 && row2 < gameBoard.getRows() && col2 >= 0 && col2 < gameBoard.getColumns();
    }

    public void resetGame() {
        if (this.gameBoard != null) {
            currentPlayer.resetScore(); // Reset the score
            startGame(gameBoard.getRows(), gameBoard.getColumns());
        }
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameInProgress() {
        return isGameInProgress;
    }

    public boolean isGameOver() {
        return !isGameInProgress;
    }
    public int getMoveCount() {
        return moveCounter;
    }



}
