package org.fliptile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class MainMenuController {
    @FXML private ToggleButton button4x4;
    @FXML private ToggleButton button6x6;
    @FXML private ToggleButton button8x8;

    private ToggleGroup group = new ToggleGroup();
    private int gridSize;

    @FXML
    private void initialize() {
        // Assign the buttons to the toggle group
        button4x4.setToggleGroup(group);
        button6x6.setToggleGroup(group);
        button8x8.setToggleGroup(group);

        group.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                String selectedLabel = ((ToggleButton)newVal).getText();
                gridSize = Integer.parseInt(selectedLabel.substring(0, 1));
                // Update the state variable with gridSize
            }
        });
    }

}
