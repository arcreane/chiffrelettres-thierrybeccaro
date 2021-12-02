package com.company;

import java.text.SimpleDateFormat;
import java.util.*;

public class Highscores {

    static public Hashtable<String, ArrayList<String>> scoresBoard = new Hashtable<String, ArrayList<String>>();

    public static void writeNamesAndScores(Player Player1, Player Player2){
        String key = Player1.name + " VS "+Player2.name;
        ArrayList<String> values = new ArrayList<String>();
        if(scoresBoard.containsKey(key)){
            StringBuilder iValues = new StringBuilder(String.valueOf(scoresBoard.get(key)));
            iValues.delete(0, 1);
            iValues.delete(7, 8);
            values.add(String.valueOf(iValues));
        }
        values.add(Player1.score+" - "+Player2.score);
        scoresBoard.put(key, values);
    }

    public static void displayHighScores() {
        System.out.println("*************** SCORES **************");
        printScores();
    }

    public static void printScores() {
        scoresBoard.forEach(
                (k, v) -> System.out.println("Players : " + k + "\t Scores : " + v));
    }
}
