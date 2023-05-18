package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

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
    public String[][] field = new String[12][18];
    public void parseField() throws FileNotFoundException {
        Scanner levelTxt=new Scanner(new File("src/main/resources/com/example/demo2/level1_field.txt"));
        for(int i=0;i<12;i++){
            String st=levelTxt.nextLine();
            st=st.substring(2,st.length()-2);
            field[i]=st.split(",");
        }
        System.out.println(field[2][1].length());
    }
    public void movePlayer(int x,int y){
        if(field[py+y][px+x].equals("0")) {
            playerId.setLayoutX(playerId.getLayoutX() + x * 50);
            playerId.setLayoutY(playerId.getLayoutY() + y * 50);
            px=px+x;
            py=py+y;
        }
    }
    public void placeBomb(MouseEvent mouseEvent) {
        bomb.setLayoutX(playerId.getLayoutX());
        bomb.setLayoutY(playerId.getLayoutY());
    }
    public void checkDeath(){
        if(enemy1.getBoundsInParent().intersects(playerId.getBoundsInParent())||enemy2.getBoundsInParent().intersects(playerId.getBoundsInParent())||enemy3.getBoundsInParent().intersects(playerId.getBoundsInParent())){
            System.out.println("death");
        }
    }
}
