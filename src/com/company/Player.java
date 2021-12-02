package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    public static String getPlayerAnswer(int time) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        System.out.println("Entrez votre réponse: ");
        while(time > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (br.ready()){
                answer = br.readLine();
                System.out.printf("Merci. Votre réponse est : %s\n", answer);
                Game.waitAWhile(2);
                return answer;
            }
            time--;
        }
        System.out.printf("Le temps imparti est écoulé. Votre réponse est : %s\n", answer);
        Game.waitAWhile(2);
        return answer;
    }


}
