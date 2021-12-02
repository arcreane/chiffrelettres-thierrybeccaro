package com.company;



public class Game {
public static boolean badAnswer;
    public static void round(){
        // 3 rounds are planned
        int round = 0;
        while(round++ < 3 && badAnswer==false ){
            // To jump to following iteration when player doesn't answer within 30s.
            badAnswer = false;
            int randInt = (int)(Math.random() * 2);
            if(randInt == 0){
                sayGameType("lettres");

                // Déroulé du jeu des Lettres
                System.out.println("Déroulé du jeu des Lettres");

                // Puis déroulé du jeu des Chiffres
                System.out.println("Déroulé du jeu des Chiffres");

            } else {
                sayGameType("chiffres");

                // Déroulé du jeu des Chiffres
                System.out.println("Déroulé du jeu des Chiffres");

                // Puis Déroulé du jeu de Lettre
                System.out.println("Déroulé du jeu des Lettres");

            }
            //Calcul des scores de chacun à la fin de la manche.
            System.out.println("Calcul des scores de chacun à la fin de la manche.");
        }
        //Affichage des scores et du gagnant.
        System.out.println("Affichage des scores et du gagnant.");
    }

    private static void sayGameType(String roundType){
        System.out.printf("Nous allons commencer par les %s\n", roundType);
    }
}
