package org.fliptile.model;

import org.fliptile.model.GameBoard;
import org.fliptile.model.Player;
import org.fliptile.model.Tile;

public class GameManager {
    private GameBoard gameBoard;
    private final Player currentPlayer;
    private boolean isGameInProgress;

    public GameManager(String playerName) {
        this.currentPlayer = new Player(playerName);
        this.isGameInProgress = false;
    }

    public void startGame(int rows, int columns) {
        this.gameBoard = new GameBoard(rows, columns);
        currentPlayer.updateScore(-currentPlayer.getScore());
        isGameInProgress = true;
    }

    public void processMove(int row1, int col1, int row2, int col2) {
        if (!isGameInProgress) {
            System.out.println("Game is not in progress. Please start a new game.");
            return;
        }

        Tile firstTile = gameBoard.getBoard()[row1][col1];
        Tile secondTile = gameBoard.getBoard()[row2][col2];

        firstTile.flip();
        secondTile.flip();
        gameBoard.displayBoard();

        if (gameBoard.checkMatch(firstTile, secondTile)) {
            currentPlayer.updateScore(1); // Update score for a successful match
            System.out.println("Match found! Score: " + currentPlayer.getScore());
        } else {
            firstTile.flip(); // Flip back if not a match
            secondTile.flip();
        }

        if (gameBoard.isGameOver()) {
            System.out.println("Game Over. Your score: " + currentPlayer.getScore());
            isGameInProgress = false;
        }
    }

    public void resetGame() {
        if(this.gameBoard != null){
            int rows = this.gameBoard.getRows();
            int columns = this.gameBoard.getColumns();
            startGame(rows, columns);
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
}
