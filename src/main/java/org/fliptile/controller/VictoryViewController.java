package org.fliptile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class VictoryViewController {
    @FXML private Label scoreLabel;
    @FXML private Label moveCountLabel;

    public void setScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public void setMoveCountLabel(int moveCount) {
        moveCountLabel.setText("Moves: " + moveCount);
    }

}
