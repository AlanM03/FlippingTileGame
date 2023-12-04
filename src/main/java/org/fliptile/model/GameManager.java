package org.fliptile.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the state and logic of the memory game.
 */
public class GameManager {
    private GameBoard gameBoard;
    private final Player currentPlayer;
    private boolean isGameInProgress;
    private int moveCounter;

    /**
     * Constructs a GameManager with a player name.
     *
     * @param playerName The name of the player.
     */
    public GameManager(String playerName) {
        this.currentPlayer = new Player(playerName);
        this.isGameInProgress = false;
        this.moveCounter = 0;
    }

    /**
     * Starts a new game with the specified number of rows and columns.
     *
     * @param rows    The number of rows in the game board.
     * @param columns The number of columns in the game board.
     */
    public void startGame(int rows, int columns) {
        List<String> imageIdentifiers = getImageIdentifiersForBoardSize((rows * columns) / 2);
        this.gameBoard = new GameBoard(rows, columns, imageIdentifiers);
        currentPlayer.resetScore();
        isGameInProgress = true;
        moveCounter = 0;
    }

    /**
     * Generates a list of image identifiers based on the number of pairs required.
     *
     * @param pairs The number of image pairs required for the game board.
     * @return A list of image identifiers.
     */
    private List<String> getImageIdentifiersForBoardSize(int pairs) {
        List<String> identifiers = new ArrayList<>();
        for (int i = 1; i <= pairs; i++) {
            identifiers.add("image" + i);
        }
        return identifiers;
    }

    /**
     * Processes a player's move, flipping two tiles and updating the game state accordingly.
     *
     * @param row1 First tile's row.
     * @param col1 First tile's column.
     * @param row2 Second tile's row.
     * @param col2 Second tile's column.
     * @return True if the game is still in progress, false if the game has ended.
     * @throws IllegalArgumentException if the move coordinates are invalid.
     */
    public boolean processMove(int row1, int col1, int row2, int col2) {
        if (!isGameInProgress) {
            return false;
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

    /**
     * Checks if a move is valid based on the game board dimensions.
     *
     * @param row1 First tile's row.
     * @param col1 First tile's column.
     * @param row2 Second tile's row.
     * @param col2 Second tile's column.
     * @return True if the move is valid, false otherwise.
     */
    private boolean isValidMove(int row1, int col1, int row2, int col2) {
        return row1 >= 0 && row1 < gameBoard.getRows() && col1 >= 0 && col1 < gameBoard.getColumns() &&
                row2 >= 0 && row2 < gameBoard.getRows() && col2 >= 0 && col2 < gameBoard.getColumns();
    }

    /**
     * Resets the game to its initial state.
     */
    public void resetGame() {
        if (this.gameBoard != null) {
            currentPlayer.resetScore();
            startGame(gameBoard.getRows(), gameBoard.getColumns());
        }
    }

    /**
     * Returns the current game board.
     *
     * @return The current game board.
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Returns the current player.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if the game is currently in progress.
     *
     * @return True if the game is in progress, false otherwise.
     */
    public boolean isGameInProgress() {
        return isGameInProgress;
    }

    /**
     * Checks if the game is over.
     *
     * @return True if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        return !isGameInProgress;
    }

    /**
     * Returns the number of moves made in the current game.
     *
     * @return The number of moves made.
     */
    public int getMoveCount() {
        return moveCounter;
    }
}
