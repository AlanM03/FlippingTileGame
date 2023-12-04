package org.fliptile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {
    @FXML private ToggleButton button4x4;
    @FXML private ToggleButton button6x6;
    @FXML private ToggleButton button8x8;
    @FXML private Button startButton;

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
                gridSize = Integer.parseInt(selectedLabel.substring(0, 1));// Updates the state variable with gridSize
                startButton.setDisable(false); // Enable the start button

            }
        });
    }

    @FXML
    private void startGame() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView.fxml"));
            Parent root = loader.load();

            GameViewController controller = loader.getController();
            controller.setGridSize(gridSize);  //Sets grid size in GameViewController

            Stage stage = (Stage) startButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

}
