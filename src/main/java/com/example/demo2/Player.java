package com.example.demo2;
public class Player {
    int x;
    int y;
    int speedX;
    int speedY;
    public void move(int mx,int my){
        x+=mx*speedX;
        y+=mx*speedY;
    };
}
