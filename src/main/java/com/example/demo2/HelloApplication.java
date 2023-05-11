package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        Image image = new Image("C:\\Users\\artyo\\Desktop\\demo2\\src\\main\\resources\\com\\example\\demo2");
        stage.getIcons().add(image);
        stage.setScene(level1);
        stage.show();
    }

    public static void main(String[] args) {
        Player player=new Player();
        int[][] field=new int[18][12];
        List<Enemy> enemies = new ArrayList<>();
        List<Bomb> bombs = new ArrayList<>();
        launch();
    }

    public void checkEnemyTouchPlayer(){}
    public void checkEnemyTouchWalls(){}
    public void checkPlayerTouchWalls(){}
    public void checkVictory(){
        if(enemies.size()==0){
            System.out.println("win");
        }
    }
}