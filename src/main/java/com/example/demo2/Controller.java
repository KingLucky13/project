package com.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Controller {

    @FXML
    public ImageView enemy1;
    @FXML
    public ImageView enemy2;
    @FXML
    public ImageView enemy3;
    boolean isEnemyAlive1=true;
    boolean isEnemyAlive2=true;
    boolean isEnemyAlive3=true;
    public ImageView bomb;
    public ImageView playerId;
    public int px=1;
    public int py=1;
    public int bx;
    public int by;
    public String[][] field = new String[12][18];
    int bombTimerStartTime;
    AnimationTimer bombTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            if((int)System.currentTimeMillis()-bombTimerStartTime>=1500){
                if(field[by][bx + 1].equals("2"))
                    field[by][bx + 1] = "0";
                if(field[by + 1][bx].equals("2"))
                    field[by + 1][bx] = "0";
                if(field[by - 1][bx].equals("2"))
                    field[by - 1][bx] = "0";
                if(field[by][bx - 1].equals("2"))
                    field[by][bx - 1] = "0";
                bomb.setLayoutX(-100);
                stop();
            }
        }
    };

    public void parseField() throws FileNotFoundException {
        Scanner levelTxt=new Scanner(new File("src/main/resources/com/example/demo2/level1_field.txt"));
        for(int i=0;i<12;i++){
            String st=levelTxt.nextLine();
            st=st.substring(2,st.length()-2);
            field[i]=st.split(",");
        }
    }
    public void movePlayer(int x,int y){
        if(field[py+y][px+x].equals("0")) {
            playerId.setLayoutX(playerId.getLayoutX() + x * 50);
            playerId.setLayoutY(playerId.getLayoutY() + y * 50);
            px=px+x;
            py=py+y;
            System.out.println(py + " " + px);
            System.out.println(field[py][px]);
        }
    }
    public void placeBomb(){
        bomb.setLayoutX(playerId.getLayoutX());
        bomb.setLayoutY(playerId.getLayoutY());
        bx=px;
        by=py;
        bombTimerStartTime= (int) System.currentTimeMillis();
        bombTimer.start();
    }
    public void checkDeath(){
        if(enemy1.getBoundsInParent().intersects(playerId.getBoundsInParent())||enemy2.getBoundsInParent().intersects(playerId.getBoundsInParent())||enemy3.getBoundsInParent().intersects(playerId.getBoundsInParent())){
            System.out.println("death");
        }
    }
    public void checkWin(){
        if(!isEnemyAlive1 && !isEnemyAlive2 && !isEnemyAlive3){
            System.out.println("win");
        }
    }
}
