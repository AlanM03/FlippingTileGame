package org.fliptile.controller;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.fliptile.model.GameManager;
import org.fliptile.model.Tile;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller class for managing the game view in a memory game.
 */
public class GameViewController {
    private int gridSize;
    private boolean isProcessingMove;

    @FXML private GridPane tileGrid;
    @FXML private Label scoreLabel;
    @FXML private Label moveCountLabel;

    private GameManager gameManager;
    private Integer firstTileRow = null, firstTileColumn = null;

    /**
     * Initializes the game manager and sets up the initial game state.
     */
    @FXML
    public void initialize() {
        gameManager = new GameManager("PlayerName");
    }

    /**
     * Sets the size of the grid for the game board and initializes the game board.
     *
     * @param gridSize The size of the grid (number of rows and columns).
     */
    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        setupGameBoard();
    }

    /**
     * Sets up the game board with clickable tiles.
     */
    private void setupGameBoard() {
        tileGrid.getChildren().clear();
        gameManager.startGame(gridSize, gridSize);
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Button tileButton = new Button();
                tileButton.setMinSize(80, 80);
                int finalRow = row;
                int finalCol = col;
                tileButton.setOnAction(e -> onTileClick(finalRow, finalCol));
                tileGrid.add(tileButton, col, row);
            }
        }
    }

    /**
     * Handles tile clicks, processing game logic based on the state of the clicked tile.
     *
     * @param row The row of the clicked tile.
     * @param col The column of the clicked tile.
     */
    public void onTileClick(int row, int col) {
        if (isProcessingMove) {
            return;
        }

        Tile clickedTile = gameManager.getGameBoard().getBoard()[row][col];
        if (clickedTile.isMatched() || clickedTile.isFlipped()) {
            return;
        }

        clickedTile.flip();
        Button tileButton = (Button) tileGrid.getChildren().get(row * gridSize + col);
        updateTileImage(clickedTile, tileButton);

        if (firstTileRow != null && firstTileColumn != null) {
            processSecondTile(row, col, clickedTile);

        } else {
            firstTileRow = row;
            firstTileColumn = col;
        }

    }

    /**
     * Processes the second tile click in a move, checking for matches and updating the game state.
     *
     * @param row The row of the second clicked tile.
     * @param col The column of the second clicked tile.
     * @param clickedTile The tile object of the clicked tile.
     */
    private void processSecondTile(int row, int col, Tile clickedTile) {
        isProcessingMove = true;
        gameManager.processMove(firstTileRow, firstTileColumn, row, col);
        updateUI();

        if (!clickedTile.matches(gameManager.getGameBoard().getBoard()[firstTileRow][firstTileColumn])) {
            pauseAndFlipBackTiles(row, col);
        } else {
            isProcessingMove = false;
            checkGameOver();
        }
        firstTileRow = null;
        firstTileColumn = null;

    }

    /**
     * Pauses the game for a brief moment before flipping back unmatched tiles.
     *
     * @param secondRow The row of the second tile.
     * @param secondCol The column of the second tile.
     */
    private void pauseAndFlipBackTiles(int secondRow, int secondCol) {
        final int savedFirstTileRow = firstTileRow;
        final int savedFirstTileColumn = firstTileColumn;

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> {
            flipBackTiles(savedFirstTileRow, savedFirstTileColumn, secondRow, secondCol);
            isProcessingMove = false;
            checkGameOver();
        });
        pause.play();
    }

    /**
     * Flips back the tiles that were not matched.
     *
     * @param firstRow The row of the first tile.
     * @param firstCol The column of the first tile.
     * @param secondRow The row of the second tile.
     * @param secondCol The column of the second tile.
     */
    private void flipBackTiles(int firstRow, int firstCol, int secondRow, int secondCol) {
        Tile firstTile = gameManager.getGameBoard().getBoard()[firstRow][firstCol];
        Tile secondTile = gameManager.getGameBoard().getBoard()[secondRow][secondCol];
        firstTile.flip();
        secondTile.flip();
        updateTileImage(firstTile, (Button) tileGrid.getChildren().get(firstRow * gridSize + firstCol));
        updateTileImage(secondTile, (Button) tileGrid.getChildren().get(secondRow * gridSize + secondCol));
    }

    /**
     * Updates the image of a tile on the game board.
     *
     * @param tile The tile to update.
     * @param tileButton The button representing the tile on the UI.
     */
    private void updateTileImage(Tile tile, Button tileButton) {
        if (tile.isFlipped()) {
            ImageView tileImage = new ImageView(tile.getImage());
            tileImage.setFitWidth(60);
            tileImage.setFitHeight(60);
            tileButton.setGraphic(tileImage);
        } else {
            tileButton.setGraphic(null);
        }
    }

    /**
     * Updates the UI elements such as the score label and move count label.
     */
    private void updateUI() {
        scoreLabel.setText("Score: " + gameManager.getCurrentPlayer().getScore());
        moveCountLabel.setText("Moves: " + gameManager.getMoveCount());
    }

    /**
     * Resets the game to its initial state.
     */
    @FXML
    public void resetGame() {
        gameManager.resetGame();
        setupGameBoard();
        updateUI();
    }

    /**
     * Checks if the game is over and transitions to the victory screen if it is.
     */
    private void checkGameOver() {
        if (gameManager.isGameOver()) {
            goToVictoryScreen();
        }
    }

    /**
     * Transitions the UI to the victory screen upon game completion.
     */
    private void goToVictoryScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Victory.fxml"));
            Parent victoryRoot = loader.load();
            VictoryViewController controller = loader.getController();


            controller.setScoreLabel(gameManager.getCurrentPlayer().getScore());
            controller.setMoveCountLabel(gameManager.getMoveCount());

            Scene victoryScene = new Scene(victoryRoot);
            Stage currentStage = (Stage) tileGrid.getScene().getWindow();
            currentStage.setScene(victoryScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transitions the UI to the main menu.
     */
    @FXML
    public void goToMainMenu() {
        try {
            Parent mainMenuRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/MainMenu.fxml")));
            Scene mainMenuScene = new Scene(mainMenuRoot);

            Stage currentStage = (Stage) tileGrid.getScene().getWindow();
            currentStage.setScene(mainMenuScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
