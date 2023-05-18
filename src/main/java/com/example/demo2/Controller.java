package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Controller {

    @FXML
    public ImageView enemy1;
    @FXML
    public ImageView enemy2;
    @FXML
    public ImageView enemy3;
    public ImageView bomb;
    public ImageView playerId;
    public int px=1;
    public int py=1;
    public int[][] field = new int[18][12];
    public void movePlayer(int x,int y){

        playerId.setLayoutX(playerId.getLayoutX()+x*50);
        playerId.setLayoutY(playerId.getLayoutY()+y*50);
    }
    public void placeBomb(MouseEvent mouseEvent) {
        bomb.setLayoutX(playerId.getLayoutX());
        bomb.setLayoutY(playerId.getLayoutY());
    }

}
