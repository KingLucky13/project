package com.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
    public Button tryAgainButton;
    Controller gameController;
    private GameWithMusic gameWithMusic; // Declare the GameWithMusic instance as a class variable
    AnimationTimer checkDeathTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            try {
                gameController.checkDeath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };
    AnimationTimer checkWinTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            try {
                gameController.checkWin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };

    AnimationTimer bombTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            try {
                wait(3000);
                System.out.println("boom");
                this.stop();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

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

    public void startButtonClicked(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("level1.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        Stage stage=(Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        gameController=fxmlLoader.getController();
        gameController.parseField();
        stage.setScene(scene);
        stage.show();
        checkDeathTimer.start();
        checkWinTimer.start();
        gameController.enemy1MoveTimer.start();
        gameController.enemy2MoveTimer.start();
        gameController.enemy3MoveTimer.start();

        scene.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if((event.getCharacter().equals("w"))||(event.getCharacter().equals("ц"))) {
                    try {
                        gameController.movePlayer(0,-1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if((event.getCharacter().equals("s"))||(event.getCharacter().equals("ы"))) {
                    try {
                        gameController.movePlayer(0,1);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if((event.getCharacter().equals("d"))||(event.getCharacter().equals("в"))) {
                    try {
                        gameController.movePlayer(1,0);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if((event.getCharacter().equals("a"))||(event.getCharacter().equals("ф"))) {
                    try {
                        gameController.movePlayer(-1,0);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                else if((event.getCharacter().equals("e"))||(event.getCharacter().equals("у")))
                    gameController.placeBomb();
            }
        });
    }
}
