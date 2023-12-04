package org.fliptile.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.fliptile.model.GameManager;

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
        if (firstTileRow == null || firstTileColumn == null) {
            firstTileRow = row;
            firstTileColumn = col;
        } else {
            gameManager.processMove(firstTileRow, firstTileColumn, row, col);
            updateUI();
            firstTileRow = null; // Reset for the next turn
            firstTileColumn = null;
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
