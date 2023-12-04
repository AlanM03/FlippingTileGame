package org.fliptile.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("Victory.fxml")));
        primaryStage.setTitle("Flipping Tile Game");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        checkImagesExistence();
        launch(args);
    }

    private static void checkImagesExistence() {
        for (int i = 1; i <= 32; i++) {
            String path = "/images/image" + i + ".png";
            InputStream is = Main.class.getResourceAsStream(path);
            if (is == null) {
                System.out.println("Cannot find image: " + path);
            } else {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
