package com.example.demo2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    Player player=new Player();
    int[][] field=new int[18][12];
    List<Enemy> enemies;
    Bomb[] bombs;
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
        stage.show();
        level1.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCharacter().equals("w")){
                    player.move(0,1);
                }
                else if (keyEvent.getCharacter().equals("s")) {
                    player.move(0,-1);
                }
                else if (keyEvent.getCharacter().equals("a")) {
                    player.move(-1,0);
                }
                else if (keyEvent.getCharacter().equals("d")) {
                    player.move(1,0);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }

    private void showVictoryScreen() {
        // Display the victory screen
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("victoryScreen.fxml"));

        Scene victoryScreen = null;
        try {
            victoryScreen = new Scene(fxmlLoader.load(), 900, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image("src/main/resources/com/example/demo2/victory.png");
        Stage stage = (Stage) player.getScene().getWindow(); // Get the current stage
        stage.setScene(victoryScreen);
        gameOver = true;
    }

    private void showDefeatScreen() {
        // Display the defeat screen
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("defeatScreen.fxml"));
        Scene defeatScreen = null;
        try {
            defeatScreen = new Scene(fxmlLoader.load(), 900, 600);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image image = new Image("src/main/resources/com/example/demo2/gameover.png");
        Stage stage = (Stage) player.getScene().getWindow(); // Get the current stage
        stage.setScene(defeatScreen);
        gameOver = true;
    }
    public void checkGameStatus() {
        if (enemies.size() == 0) {
            System.out.println("You win!");
            showVictoryScreen();
        } else if (enemies.size()>=1) {
            System.out.println("Game over!");
            showDefeatScreen();
        }
    }



}