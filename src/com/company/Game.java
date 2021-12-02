package com.company;

import java.io.IOException;


public class Game {

    public static boolean badAnswer;

    public static void round(String vs) throws IOException {

        // 3 rounds are planned
        int round = 0;
        while(round++ < 3 && !badAnswer){
            waitAWhile(2);
            Main.clearScreen();
            System.out.printf("MANCHE %d\n", round);
            // To jump to following iteration when player doesn't answer within 30s.
            badAnswer = false;
            int randInt = (int)(Math.random() * 2);
            if(randInt == 0){
                sayGameType("lettres");

                // Déroulé du jeu des Lettres
                if(vs.equals("1V1")){
                    Lettres.JeuLettre1V1();
                    Chiffres.nbrSelect(StartGame.Player1, StartGame.Player2);
                } else{
                    System.out.println("Jeu des Lettres 1 Vs IA");
                // Puis déroulé du jeu des Chiffres
                    Chiffres.nbrSelectVsComputer(StartGame.Player2, StartGame.computer);
                }

            } else {
                sayGameType("chiffres");

                if(vs.equals("1V1")){
                    Chiffres.nbrSelect(StartGame.Player1, StartGame.Player2);
                    // Puis déroulé du jeu des Lettres
                    Lettres.JeuLettre1V1();
                } else{
                    Chiffres.nbrSelectVsComputer(StartGame.Player2, StartGame.computer);
                    // Puis déroulé du jeu des Chiffres
                    System.out.println("Déroulé du jeu des Lettres");
                }
            }
            //Calcul des scores de chacun à la fin de la manche.

            System.out.println("Calcul des scores de chacun à la fin de la manche.");
        }
        //Display of the winner and their Scores
        waitAWhile(2);
        Main.clearScreen();
        //Faux gagant
        String winner = "Titi";
        System.out.printf("********* Et le gagnant est : %s *********\n", winner);
        Highscores.displayHighScores();
        waitAWhile(3);
    }

    private static void sayGameType(String roundType){
        System.out.printf("Nous allons commencer par les %s\n", roundType);
    }

    public static void waitAWhile(int millis){
        try {
            Thread.sleep(millis*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
