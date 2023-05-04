package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {
    Player player=new Player();
    int[][] field=new int[18][12];
    List<Enemy> enemies;
    Bomb[] bombs;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public void checkEnemyTouchPlayer(){}
    public void checkEnemyTouchWalls(){}
    public void checkPlayerTouchWalls(){}
    public void checkVictory(){
        if(enemies.size()==0){
            System.out.println("win");
        }
    }
    public static void main(String[] args) {
        launch();
    }
}