package com.company;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Highscores {


    static ArrayList<ArrayList<String>> scoresBoard = new ArrayList<>();

    public static void writeNamesAndScores(Player Player1, Player Player2){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        int index = scoresBoard.size();
        scoresBoard.get(index).add(formatter.format(date));
        scoresBoard.get(index).add(Player1.name + " VS " + Player2.name);
        scoresBoard.get(index).add(Player1.score + " " + Player2.score);

    }

    public static void displayHighScores() {
        String level = "";
        int tabIndex = 0;
        System.out.println("****** SCORES ******");
        printScores(tabIndex, level);
    }

    public static void printScores(int index, String level){
        System.out.println("Names: " + scoresBoard.get(0) + " " + scoresBoard.get(1)+" " + scoresBoard.get(2));
}
