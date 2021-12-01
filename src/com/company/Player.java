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

    /**
     * Player has a determined time to answer.
     */
    public static String getPlayerAnswer(){
        Timer.DisplayTimer("Temps pour entrer votre réponse: ", 30);
        String answer = Main.sc.nextLine();
        Main.sc.nextLine();
        return answer;
    }

}
