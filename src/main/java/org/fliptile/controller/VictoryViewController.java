package org.fliptile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Controller class for the victory screen in the memory game.
 */
public class VictoryViewController {
    @FXML private Label scoreLabel;
    @FXML private Label moveCountLabel;

    /**
     * Sets the score label with the provided score.
     *
     * @param score The final score achieved by the player.
     */
    public void setScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }

    /**
     * Sets the move count label with the provided move count.
     *
     * @param moveCount The number of moves taken by the player.
     */
    public void setMoveCountLabel(int moveCount) {
        moveCountLabel.setText("Moves: " + moveCount);
    }

}
