package com.example.demo2;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    public Pane booms;
    public Pane patches;
    boolean isEnemyAlive1=true;
    boolean isEnemyAlive2=true;
    boolean isEnemyAlive3=true;
    public ImageView bomb;
    public ImageView playerId;
    public ImageView boom;
    public int px=1;
    public int py=1;
    public int bx;
    public int by;
    public int mx;
    public int my;
    public int e1x=5;
    public int e1y=10;
    public int ne1x=0;
    public int ne1y=1;
    public String[][] field = new String[12][18];
    int bombTimerStartTime;
    int moveTime=0;
    boolean isMoving=false;
    boolean checkDeath=false;
    public ArrayList<ImageView> enemiesList;

    {
        enemiesList = new ArrayList<ImageView>();
    }


    AnimationTimer enemy1MoveTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            enemy1.setLayoutX(enemy1.getLayoutX() + ne1x * 2);
            enemy1.setLayoutY(enemy1.getLayoutY() + ne1y * 2);
            moveTime+=1;
            if(moveTime==25){
                moveTime=0;
                stop();
            }
        }
    };
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
    AnimationTimer boomTimer1 = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if((int)System.currentTimeMillis() - bombTimerStartTime >= 1000){
                bomb.setLayoutX(-100);
                boom.setLayoutX(bx * 50);
                boom.setLayoutY(by * 50);
                if (field[by][bx].equals("3")){
                    checkDeath = true;
                    bombTimer.stop();
                }
                stop();
            }
        }
    };
    AnimationTimer boomTimer2 = new AnimationTimer() {
        @Override
        public void handle(long l) {
            if((int)System.currentTimeMillis() - bombTimerStartTime >= 1600){
                if(!field[by][bx+1].equals("1")){
                    for(int i = 0; i < booms.getChildren().size(); i++){
                        if((booms.getChildren().get(i).getLayoutX() == -100)){
                            booms.getChildren().get(i).setLayoutY(by * 50);
                            booms.getChildren().get(i).setLayoutX((bx + 1) * 50);
                            break;
                        }
                    }
                }
                if(!field[by][bx-1].equals("1")){
                    for(int i = 0; i < booms.getChildren().size(); i++){
                        if((booms.getChildren().get(i).getLayoutX() == -100)){
                            booms.getChildren().get(i).setLayoutY(by * 50);
                            booms.getChildren().get(i).setLayoutX((bx - 1) * 50);
                            break;
                        }
                    }
                }
                if(!field[by+1][bx].equals("1")){
                    for(int i = 0; i < booms.getChildren().size(); i++){
                        if((booms.getChildren().get(i).getLayoutX() == -100)){
                            booms.getChildren().get(i).setLayoutX(bx * 50);
                            booms.getChildren().get(i).setLayoutY((by + 1) * 50);
                            break;
                        }
                    }
                }
                if(!field[by-1][bx].equals("1")){
                    for(int i = 0; i < booms.getChildren().size(); i++){
                        if((booms.getChildren().get(i).getLayoutX() == -100)){
                            booms.getChildren().get(i).setLayoutX(bx * 50);
                            booms.getChildren().get(i).setLayoutY((by - 1) * 50);
                            break;
                        }
                    }
                }
                stop();
            }
        }
    };
    AnimationTimer bombTimer=new AnimationTimer() {
        @Override
        public void handle(long l) {
            if((int)System.currentTimeMillis()-bombTimerStartTime>=1800){
                int y = 0;
                int x = 0;
                boom.setLayoutX(-100);
                for(int i = 0; i < booms.getChildren().size(); i++){
                    booms.getChildren().get(i).setLayoutX(-100);
                }
                if(field[by][bx + 1].equals("2")) {
                    field[by][bx + 1] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx + 1)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                    for (int i = 0; i < patches.getChildren().size(); i++){
                        if(patches.getChildren().get(i).getLayoutX() == -100){
                            patches.getChildren().get(i).setLayoutX((bx+1) * 50);
                            patches.getChildren().get(i).setLayoutY(by * 50);
                            break;
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
                if(field[by+1][bx].equals("2")) {
                    field[by+1][bx] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by+1))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                    for (int i = 0; i < patches.getChildren().size(); i++){
                        if(patches.getChildren().get(i).getLayoutX() == -100){
                            patches.getChildren().get(i).setLayoutX((bx) * 50);
                            patches.getChildren().get(i).setLayoutY((by+1) * 50);
                            break;
                        }
                    }
                }
                if (field[by+1][bx].equals("4")){
                    field[by+1][bx] = "0";
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
                if(field[by][bx-1].equals("2")) {
                    field[by][bx-1] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx-1)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                    for (int i = 0; i < patches.getChildren().size(); i++){
                        if(patches.getChildren().get(i).getLayoutX() == -100){
                            patches.getChildren().get(i).setLayoutX((bx-1) * 50);
                            patches.getChildren().get(i).setLayoutY(by * 50);
                            break;
                        }
                    }
                }
                if (field[by][bx-1].equals("4")){
                    field[by][bx-1] = "0";
                    for(int i = 0; i < enemies.getChildren().size(); i++){
                        if((enemies.getChildren().get(i).getLayoutX() == 50 * (bx-1)) && (enemies.getChildren().get(i).getLayoutY() == 50 * (by))){
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
                if(field[by-1][bx].equals("2")) {
                    field[by-1][bx] = "0";
                    for(int i = 0; i < stones.getChildren().size(); i++){
                        if((stones.getChildren().get(i).getLayoutX() == 50 * (bx)) && (stones.getChildren().get(i).getLayoutY() == 50 * (by-1))){
                            stones.getChildren().get(i).setLayoutX(-100);
                        }
                    }
                    for (int i = 0; i < patches.getChildren().size(); i++){
                        if(patches.getChildren().get(i).getLayoutX() == -100){
                            patches.getChildren().get(i).setLayoutX((bx) * 50);
                            patches.getChildren().get(i).setLayoutY((by-1) * 50);
                            break;
                        }
                    }
                }
                if (field[by-1][bx].equals("4")){
                    field[by-1][bx] = "0";
                    for(int i = 0; i < enemies.getChildren().size(); i++){
                        if((enemies.getChildren().get(i).getLayoutX() == 50 * (bx)) && (enemies.getChildren().get(i).getLayoutY() == 50 * (by-1))){
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
                if (field[by][bx].equals("4")){
                    field[by][bx] = "0";
                    for(int i = 0; i < enemies.getChildren().size(); i++){
                        if((enemies.getChildren().get(i).getLayoutX() == 50 * (bx)) && (enemies.getChildren().get(i).getLayoutY() == 50 * (by))){
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
                String bombLoyout1 = field[by + 1][bx];
                String bombLoyout2 = field[by][bx + 1];
                String bombLoyout3 = field[by- 1][bx];
                String bombLoyout4 = field[by][bx - 1];
                if((field[by][bx].equals("3")) || (Objects.equals(bombLoyout1, "3")) || (Objects.equals(bombLoyout2, "3")) || (Objects.equals(bombLoyout3, "3")) || (Objects.equals(bombLoyout4, "3"))){
                    checkDeath = true;
                }
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

    public void moveEnemy1() {
        if(Math.abs(px-e1x)<=3 && Math.abs(py-e1y)<=3){
            if((px-e1x)<=0 && (py-e1y)>=0 && (field[e1y+1][e1x].equals("0") || field[e1y+1][e1x].equals("3"))){
                ne1x=0;
                ne1y=1;
                field[e1y+1][e1x] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y+1;
                e1x=e1x+0;
                enemy1MoveTimer.start();
            }else if((px-e1x)<=0 && (py-e1y)>=0 && (field[e1y][e1x-1].equals("0") || field[e1y][e1x-1].equals("3"))){
                ne1x=-1;
                ne1y=0;
                field[e1y][e1x-1] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y;
                e1x=e1x-1;
                enemy1MoveTimer.start();
            }
            if((px-e1x)<0 && (py-e1y)<0 && (field[e1y-1][e1x].equals("0") || field[e1y-1][e1x].equals("3"))){
                ne1x=0;
                ne1y=-1;
                field[e1y-1][e1x] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y-1;
                e1x=e1x;
                enemy1MoveTimer.start();
            }else if((px-e1x)<0 && (py-e1y)<0 && (field[e1y][e1x-1].equals("0") || field[e1y][e1x-1].equals("3"))){
                ne1x=-1;
                ne1y=0;
                field[e1y][e1x-1] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y;
                e1x=e1x-1;
                enemy1MoveTimer.start();
            }
            if((px-e1x)>=0 && (py-e1y)<=0 && (field[e1y][e1x+1].equals("0") || field[e1y][e1x+1].equals("3"))){
                ne1x=1;
                ne1y=0;
                field[e1y][e1x+1] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y;
                e1x=e1x+1;
                enemy1MoveTimer.start();
            }else if((px-e1x)>=0 && (py-e1y)<=0 && (field[e1y-1][e1x].equals("0") || field[e1y-1][e1x].equals("3"))){
                ne1x=0;
                ne1y=-1;
                field[e1y-1][e1x] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y-1;
                e1x=e1x+0;
                enemy1MoveTimer.start();
            }
            if((px-e1x)>0 && (py-e1y)>0 && (field[e1y+1][e1x].equals("0") || field[e1y+1][e1x].equals("3"))){
                ne1x=0;
                ne1y=1;
                field[e1y+1][e1x] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y+1;
                e1x=e1x;
                enemy1MoveTimer.start();
            }else if((px-e1x)>0 && (py-e1y)>0 && (field[e1y][e1x+1].equals("0") || field[e1y][e1x+1].equals("3"))){
                ne1x=1;
                ne1y=0;
                field[e1y][e1x+1] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y;
                e1x=e1x+1;
                enemy1MoveTimer.start();
            }
        }else{
            if(field[e1y+1][e1x].equals("0") || field[e1y+1][e1x].equals("3")){
                ne1x=0;
                ne1y=1;
                field[e1y+1][e1x] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y+1;
                e1x=e1x+0;
                enemy1MoveTimer.start();
            }
            if(field[e1y][e1x+1].equals("0") || field[e1y][e1x+1].equals("3")){
                ne1x=1;
                ne1y=0;
                field[e1y][e1x+1] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y+0;
                e1x=e1x+1;
                enemy1MoveTimer.start();
            }
            if(field[e1y][e1x-1].equals("0") || field[e1y][e1x-1].equals("3")){
                ne1x=-1;
                ne1y=0;
                field[e1y][e1x-1] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y+0;
                e1x=e1x-1;
                enemy1MoveTimer.start();
            }
            if(field[e1y-1][e1x].equals("0") || field[e1y-1][e1x].equals("3")){
                ne1x=0;
                ne1y=-1;
                field[e1y-1][e1x] = "4";
                field[e1y][e1x] = "0";
                e1y=e1y-1;
                e1x=e1x+0;
                enemy1MoveTimer.start();
            }
        }
    }

    public void placeBomb(){
        if(!isMoving){
        bomb.setLayoutX(playerId.getLayoutX());
        bomb.setLayoutY(playerId.getLayoutY());
        bx=px;
        by=py;
        bombTimerStartTime= (int) System.currentTimeMillis();
        boomTimer1.start();
        boomTimer2.start();
        bombTimer.start();
        }
    }
    public void checkDeath() throws IOException {
        if(checkDeath){
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("defeatScreen.fxml"));
            Scene scene = new Scene(fxmlLoader2.load(), 900, 600);
            Stage stage = (Stage) playerId.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("Death");
            checkDeath = false;
        }
    }
    public void checkWin() throws IOException {
        if(!isEnemyAlive1 && !isEnemyAlive2 && !isEnemyAlive3){
            FXMLLoader fxmlLoader2 = new FXMLLoader(HelloApplication.class.getResource("victoryScreen.fxml"));
            Scene scene = new Scene(fxmlLoader2.load(), 900, 600);
            Stage stage = (Stage) playerId.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            System.out.println("win");
            isEnemyAlive1 = true;
            isEnemyAlive2 = true;
            isEnemyAlive3 = true;
        }
    }
}
