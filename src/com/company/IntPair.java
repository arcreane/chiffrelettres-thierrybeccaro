package com.company;

public class IntPair {
    int x;
    int y;

    public IntPair(int x,int y){
        this.x=x;
        this.y=y;
    }
    public String toString() {
        String taRace = ("pair = "+ this.x + " : " +this.y);
        return taRace;
    }

}
