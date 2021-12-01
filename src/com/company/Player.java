package com.company;

public class Player {
    String name;
    int score = 0;

    public Player(String name){
        this.name = name;
    }

    public void setScore(int score){
        this.score += score;
    }

    public int getScore(){
        return this.score;
    }

}
