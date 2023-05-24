package com.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Controller {

    @FXML
    public ImageView enemy1;
    @FXML
    public ImageView enemy2;
    @FXML
    public ImageView enemy3;
    @FXML
    public Pane stones;
    public Pane enemies;
    boolean isEnemyAlive1=true;
    boolean isEnemyAlive2=true;
    boolean isEnemyAlive3=true;
    public ImageView bomb;
    public ImageView playerId;
    public int px=1;
    public int py=1;
    public int bx;
    public int by;
    public int mx;
    public int my;
    public String[][] field = new String[12][18];
    int bombTimerStartTime;
    int moveTime=0;
    boolean isMoving=false;
    boolean checkDeath=false;
    AnimationTimer playerMoveTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            playerId.setLayoutX(playerId.getLayoutX() + mx * 2);
            playerId.setLayoutY(playerId.getLayoutY() + my * 2);
            moveTime+=1;
            if(moveTime==25){
                moveTime=0;
                isMoving=false;
                stop();
            }
        }
    };
    AnimationTimer bombTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            if((int)System.currentTimeMillis()-bombTimerStartTime>=1500){
                int y = 0;
                int x = 0;
                if(field[by][bx + 1].equals("2")) {
                    field[by][bx + 1] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx + 1)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                }
                if (field[by][bx+1].equals("4")){
                    field[by][bx+1] = "0";
                    for(int i = 0; i < enemies.getChildren().size(); i++){
                        if((enemies.getChildren().get(i).getLayoutX() == 50 * (bx + 1)) && (enemies.getChildren().get(i).getLayoutY() == 50 * (by))){
                            enemies.getChildren().get(i).setVisible(false);
                        }
                    }
                    if(isEnemyAlive1){
                        isEnemyAlive1 = false;
                    }else if(isEnemyAlive2){
                        isEnemyAlive2 = false;
                    }else {
                        isEnemyAlive3 = false;
                    }
                }
                if(field[by + 1][bx].equals("2")){
                    field[by + 1][bx] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by + 1))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                }
                if (field[by+1][bx].equals("4")){
                    field[by + 1][bx] = "0";
                    for(int i = 0; i < enemies.getChildren().size(); i++){
                        if((enemies.getChildren().get(i).getLayoutX() == 50 * (bx)) && (enemies.getChildren().get(i).getLayoutY() == 50 * (by+1))){
                            enemies.getChildren().get(i).setVisible(false);
                        }
                    }
                    if(isEnemyAlive1){
                        isEnemyAlive1 = false;
                    }else if(isEnemyAlive2){
                        isEnemyAlive2 = false;
                    }else {
                        isEnemyAlive3 = false;
                    }
                }
                if(field[by - 1][bx].equals("2")) {
                    field[by - 1][bx] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by - 1))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                }
                if (field[by - 1][bx].equals("4")){
                    field[by - 1][bx] = "0";
                    for(int i = 0; i < enemies.getChildren().size(); i++){
                        if((enemies.getChildren().get(i).getLayoutX() == 50 * (bx)) && (enemies.getChildren().get(i).getLayoutY() == 50 * (by-1))){
                            enemies.getChildren().get(i).setVisible(false);
                        }
                        if(isEnemyAlive1){
                            isEnemyAlive1 = false;
                        }else if(isEnemyAlive2){
                            isEnemyAlive2 = false;
                        }else {
                            isEnemyAlive3 = false;
                        }
                    }
                }
                if(field[by][bx - 1].equals("2")) {
                    field[by][bx - 1] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx - 1)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                }
                if (field[by][bx-1].equals("4")){
                    field[by][bx - 1] = "0";
                    for(int i = 0; i < enemies.getChildren().size(); i++){
                        if((enemies.getChildren().get(i).getLayoutX() == 50 * (bx - 1)) && (enemies.getChildren().get(i).getLayoutY() == 50 * (by))){
                            enemies.getChildren().get(i).setVisible(false);
                        }
                        if(isEnemyAlive1){
                            isEnemyAlive1 = false;
                        }else if(isEnemyAlive2){
                            isEnemyAlive2 = false;
                        }else {
                            isEnemyAlive3 = false;
                        }
                    }
                }
                String bombLoyout1 = field[by + 1][bx];
                String bombLoyout2 = field[by][bx + 1];
                String bombLoyout3 = field[by- 1][bx];
                String bombLoyout4 = field[by][bx - 1];
                if((field[by][bx].equals("3")) || (Objects.equals(bombLoyout1, "3")) || (Objects.equals(bombLoyout2, "3")) || (Objects.equals(bombLoyout3, "3")) || (Objects.equals(bombLoyout4, "3"))){
                    checkDeath = true;
                }
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
    public void movePlayer(int x,int y) throws IOException, InterruptedException {
        if(field[py+y][px+x].equals("4")){
            if(!isMoving){
            checkDeath = true;
            }
        }
        if(field[py+y][px+x].equals("0") && !isMoving) {
            isMoving=true;
            mx=x;
            my=y;
            field[py+y][px+x] = "3";
            field[py][px] = "0";
            px=px+x;
            py=py+y;
            playerMoveTimer.start();
        }
    }
    public void placeBomb(){
        if(!isMoving){
        bomb.setLayoutX(playerId.getLayoutX());
        bomb.setLayoutY(playerId.getLayoutY());
        bx=px;
        by=py;
        bombTimerStartTime= (int) System.currentTimeMillis();
        bombTimer.start();
        }
    }
    public void checkDeath(){
        if(checkDeath){
            System.out.println("Death");
            checkDeath = false;
        }
    }
    public void checkWin(){
        if(!isEnemyAlive1 && !isEnemyAlive2 && !isEnemyAlive3){
            System.out.println("win");
        }
    }
}
