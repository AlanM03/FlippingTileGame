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

public class GameViewController {

    private int gridSize;

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
        setupGameBoard();
    }

    @FXML private GridPane tileGrid;
    @FXML private Label scoreLabel;
    @FXML private Label moveCountLabel;

    private GameManager gameManager;
    private Integer firstTileRow = null, firstTileColumn = null;

    @FXML
    public void initialize() {
        gameManager = new GameManager("PlayerName");
    }

    private void setupGameBoard() {
        gameManager.startGame(gridSize, gridSize);
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                Button tileButton = new Button();
                tileButton.setMinSize(50, 50);
                int finalRow = row;
                int finalCol = col;
                tileButton.setOnAction(e -> onTileClick(finalRow, finalCol));
                tileGrid.add(tileButton, col, row);
            }
        }
    }

    public void onTileClick(int row, int col) {
        Tile clickedTile = gameManager.getGameBoard().getBoard()[row][col];
        Button tileButton = (Button) tileGrid.getChildren().get(row * gridSize + col);

        // Flip the tile
        clickedTile.flip();

        // Update tile image
        updateTileImage(clickedTile, tileButton);

        // If it was the second tile clicked, process the move
        if (firstTileRow != null && firstTileColumn != null && !(firstTileRow == row && firstTileColumn == col)) {
            gameManager.processMove(firstTileRow, firstTileColumn, row, col);
            updateUI();
            // Flip the tiles back if they are not a match after a short delay
            if (!clickedTile.matches(gameManager.getGameBoard().getBoard()[firstTileRow][firstTileColumn])) {
                final int savedFirstTileRow = firstTileRow;
                final int savedFirstTileColumn = firstTileColumn;

                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event -> {
                    // Use savedFirstTileRow and savedFirstTileColumn here
                    Tile firstTile = gameManager.getGameBoard().getBoard()[savedFirstTileRow][savedFirstTileColumn];
                    Button firstButton = (Button) tileGrid.getChildren().get(savedFirstTileRow * gridSize + savedFirstTileColumn);
                    firstTile.flip();
                    clickedTile.flip();
                    updateTileImage(firstTile, firstButton);
                    updateTileImage(clickedTile, tileButton);
                });
                pause.play();
            }
            firstTileRow = null;
            firstTileColumn = null;
        } else {
            firstTileRow = row;
            firstTileColumn = col;
        }
    }

    private void updateTileImage(Tile tile, Button tileButton) {
        if (tile.isFlipped()) {
            ImageView tileImage = new ImageView(tile.getImage());
            tileImage.setFitWidth(50);
            tileImage.setFitHeight(50);
            tileButton.setGraphic(tileImage);
        } else {
            tileButton.setGraphic(null);
        }
    }

    private void updateUI() {
        scoreLabel.setText("Score: " + gameManager.getCurrentPlayer().getScore());
        moveCountLabel.setText("Moves: " + gameManager.getMoveCount());
    }

    @FXML
    public void resetGame() {
        gameManager.resetGame();
        setupGameBoard();
        updateUI();
    }

    @FXML
    public void goToMainMenu() {
        try {
            Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
            Scene mainMenuScene = new Scene(mainMenuRoot);

            Stage currentStage = (Stage) tileGrid.getScene().getWindow();
            currentStage.setScene(mainMenuScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
