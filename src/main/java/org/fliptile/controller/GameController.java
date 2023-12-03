package org.fliptile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.fliptile.model.GameManager;

public class GameController {

    @FXML
    private GridPane gameBoard;

    @FXML
    private Label scoreLabel;

    @FXML
    private Label moveCountLabel;

    private GameManager gameManager;

    @FXML
    public void initialize() {
        // Initialize your game manager and set up the game board
    }

    // Add methods to handle game actions, update the UI, etc.
}
