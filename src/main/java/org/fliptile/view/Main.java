package org.fliptile.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The main application class for the Flipping Tile Game.
 * This class is responsible for loading and displaying the initial UI to the user.
 */
public class Main extends Application {

    /**
     * Starts the primary stage of the application, setting up the main menu.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set.
     * @throws Exception if there is any issue in loading the FXML file or setting up the stage.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("MainMenu.fxml")));
        primaryStage.setTitle("Flipping Tile Game");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param args command line arguments passed to the application.
     *             An application may get these parameters using the getParameters() method.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
