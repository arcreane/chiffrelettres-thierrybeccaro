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
                    Lettres.JeuLettre1V1(StartGame.Player1,StartGame.Player2);
                    Chiffres.nbrSelect(StartGame.Player1, StartGame.Player2);
                } else{
                    Lettres.JeuLettre1vsIA(StartGame.Player,StartGame.computer);
                    System.out.println("Déroulé du jeu des Chiffres");
                }
                // Puis déroulé du jeu des Chiffres
                    Chiffres.nbrSelectVsComputer(StartGame.Player2, StartGame.computer);


            } else {
                sayGameType("chiffres");

                if(vs.equals("1V1")){
                    Chiffres.nbrSelect(StartGame.Player1, StartGame.Player2);
                    // Puis déroulé du jeu des Lettres
                    Lettres.JeuLettre1V1(StartGame.Player1,StartGame.Player2);
                } else{
                    Lettres.JeuLettre1vsIA(StartGame.Player, StartGame.computer);
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
        String winner = null;
        if (StartGame.Player1.score > StartGame.Player2.score){
            winner = StartGame.Player1.name;
            System.out.printf("********* Et le gagnant est : %s *********\n", winner);

        }
        else if (StartGame.Player2.score > StartGame.Player1.score){
            winner = StartGame.Player2.name;
            System.out.printf("********* Et le gagnant est : %s *********\n", winner);

        }
        else if (StartGame.Player.score > StartGame.computer.score){
            winner = StartGame.Player.name;
            System.out.printf("********* Et le gagnant est : %s *********\n", winner);

        }
        else if (StartGame.computer.score > StartGame.Player.score){
            winner = StartGame.computer.name;
            System.out.printf("********* Et le gagnant est : %s *********\n", winner);

        }
        else if (StartGame.Player1.score == StartGame.Player2.score){
            System.out.println("********* Il n'y a pas de gagnant, c'est une égalité");
        }
        else if(StartGame.computer.score == StartGame.Player.score){
            System.out.println("********* Il n'y a pas de gagnant, c'est une égalité");
        }

        if(vs.equals("1V1")) {
            Highscores.writeNamesAndScores(StartGame.Player1, StartGame.Player2);
        }else{
            Highscores.writeNamesAndScores(StartGame.Player1, StartGame.computer);
        }
        Highscores.displayHighScores();
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
