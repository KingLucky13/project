package com.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class HelloApplication extends Application {
    @FXML
    public Button startButton;
    Controller gameController;
    List<Enemy> enemies;
    Bomb[] bombs;
    private GameWithMusic gameWithMusic; // Declare the GameWithMusic instance as a class variable
    AnimationTimer checkTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            gameController.checkDeath();
        }
    };
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(HelloApplication.class.getResource("homeScreen.fxml"));
        Scene homeScreen = new Scene(fxmlLoader1.load(), 900, 600);
        stage.setTitle("BOMBASTIC");
        Image image = new Image("file:main/java/resources/com.example.demo2/bomb_forlcon.png");
        stage.getIcons().add(image);
        stage.setScene(homeScreen);


        gameWithMusic = new GameWithMusic(); // Instantiate the GameWithMusic class

        stage.show();
        // hook for stopping the music
        stage.setOnCloseRequest(event -> {
            gameWithMusic.stopMusic();
        });
    }

    public static void main(String[] args) throws IOException {
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

    public void startButtonClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("level1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage = new Stage();
        gameController=fxmlLoader.getController();
        gameController.parseField();
        System.out.println(gameController);
        stage.setScene(scene);
        stage.show();
        checkTimer.start();
        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCharacter().equals("w"))
                gameController.movePlayer(0,-1);
                else if(event.getCharacter().equals("s"))
                    gameController.movePlayer(0,1);
                else if(event.getCharacter().equals("d"))
                    gameController.movePlayer(1,0);
                else if(event.getCharacter().equals("a"))
                    gameController.movePlayer(-1,0);
            }
        });
    }
}
