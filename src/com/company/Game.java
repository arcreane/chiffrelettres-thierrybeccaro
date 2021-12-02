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
                    System.out.println("Déroulé du jeu des Chiffres");
                } else{
                    System.out.println("Jeu des Lettres 1 Vs IA");
                    System.out.println("Déroulé du jeu des Chiffres");
                }
                // Puis déroulé du jeu des Chiffres

            } else {
                sayGameType("chiffres");

                if(vs.equals("1V1")){
                    System.out.println("Jeu des Lettres 1 Vs 1");
                    // Puis déroulé du jeu des Chiffres
                    System.out.println("Déroulé du jeu des Chiffres");
                } else{
                    Lettres.JeuLettre1V1();
                    // Puis déroulé du jeu des Chiffres
                    System.out.println("Déroulé du jeu des Chiffres");
                }
            }
            //Calcul des scores de chacun à la fin de la manche.

            System.out.println("Calcul des scores de chacun à la fin de la manche.");
        }
        //Affichage des scores et du gagnant.
        waitAWhile(2);
        Main.clearScreen();
        System.out.println("Affichage des scores et du gagnant.");
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
