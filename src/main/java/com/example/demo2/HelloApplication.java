package com.example.demo2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    Player player = new Player();
    int[][] field = new int[18][12];
    List<Enemy> enemies;
    Bomb[] bombs;
    private GameWithMusic gameWithMusic; // Declare the GameWithMusic instance as a class variable

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("level1.fxml"));
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("homeScreen.fxml"));
        Scene homeScreen = new Scene(fxmlLoader1.load(), 900, 600);
        Scene level1 = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("BOMBASTIC");
        Image image = new Image("file:main/java/resources/com.example.demo2/bomb_forlcon.png");
        stage.getIcons().add(image);
        stage.setScene(homeScreen);

        gameWithMusic = new GameWithMusic(); // Instantiate the GameWithMusic class

        stage.show();
        level1.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCharacter().equals("w")) {
                    player.move(0, 1);
                } else if (keyEvent.getCharacter().equals("s")) {
                    player.move(0, -1);
                } else if (keyEvent.getCharacter().equals("a")) {
                    player.move(-1, 0);
                } else if (keyEvent.getCharacter().equals("d")) {
                    player.move(1, 0);
                }
            }
        });

        // Add a shutdown hook to stop the music when the application is closed
        stage.setOnCloseRequest(event -> {
            gameWithMusic.stopMusic();
        });
    }

    public static void main(String[] args) {
        launch();
    }

    public void checkEnemyTouchPlayer() {
    }

    public void checkEnemyTouchWalls() {
    }

    public void checkPlayerTouchWalls() {
    }

    public void checkVictory() {
        if (enemies.size() == 0) {
            System.out.println("win");
        }
    }
}
