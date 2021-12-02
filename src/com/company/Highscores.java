package com.company;

public class Highscores {


    static String[][] scoresBoard = new String[0][0];

    public static void display_High_Scores() {
        String level = "";
        int tabIndex = 0;
        System.out.println("****** SCORES ******");
        print_Scores(tabIndex, level);
    }

    public static void print_Scores(int index, String level){
        for (int i = index; i < index+5; i++)
            System.out.println("Names: " + scoresBoard[i][0] + " " + scoresBoard[i][1]);
    }
}
