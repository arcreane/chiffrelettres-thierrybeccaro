package com.company;

import java.util.List;
import java.util.stream.Collectors;

public class Game {

    public static boolean badAnswer;

    public static void round(String vs) throws Exception {

        // 3 rounds are planned
        int round = 0;
        while(round++ < 3 && !badAnswer){
            waitAWhile(2);
            Main.clearScreen();
            System.out.printf("MANCHE %d\n\n", round);
            // To jump to following iteration when player doesn't answer within 30s.
            badAnswer = false;
            int randInt = (int)(Math.random() * 2);
            if(randInt == 0){
                sayGameType("lettres");
                if(vs.equals("1V1")){
                    Lettres.JeuLettre1V1(StartGame.Player1,StartGame.Player2);
                    nbreGame(Chiffres.nbrSelect(StartGame.Player1, StartGame.Player2), "1V1");
                } else{
                    Lettres.JeuLettre1vsIA(StartGame.Player,StartGame.computer);
                    nbreGame(Chiffres.nbrSelectVsComputer(StartGame.Player, StartGame.computer), "1Via");
                }


            } else {
                sayGameType("chiffres");

                if(vs.equals("1V1")){
                    nbreGame(Chiffres.nbrSelect(StartGame.Player1, StartGame.Player2), "1V1");
                    Lettres.JeuLettre1V1(StartGame.Player1,StartGame.Player2);
                } else{
                    nbreGame(Chiffres.nbrSelectVsComputer(StartGame.Player, StartGame.computer), "1Via");
                    Lettres.JeuLettre1vsIA(StartGame.Player, StartGame.computer);
                }
            }
            //Calculation of scores for each game.
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

    private static void nbreGame(List list, String mode) throws Exception {
        Main.clearScreen();
        String text = (String) list.stream().map(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println();
        Main.artGen.printTextArt(text,
                ASCIIArtGenerator.ART_SIZE_MEDIUM,
                ASCIIArtGenerator.ASCIIArtFont.ART_FONT_SANS_SERIF,
                "░");
        System.out.println();

        //Display target number
        System.out.println("Et voici le nombre à trouver :");
        int target = AlgoNombres.randomNbr(101, 999);
        int solution = AlgoNombres.findAllSolutions(list,target);
        String targetText = String.valueOf(target);
        System.out.println();
        Main.artGen.printTextArt(targetText,
                ASCIIArtGenerator.ART_SIZE_MEDIUM,
                ASCIIArtGenerator.ASCIIArtFont.ART_FONT_SANS_SERIF,
                "░");
        System.out.println();

        Timer.DisplayTimer("Il vous reste : ", 60);
        //Get answers
        if(mode.equals("1V1")){
            String answP1 = Player.getPlayerAnswer(30);
            Chiffres.answerCheck(answP1, target);
            String answP2 = Player.getPlayerAnswer(30);
            Chiffres.answerCheck(answP2, target);
        } else {
            String answP = Player.getPlayerAnswer(30);
            Chiffres.answerCheck(answP, target);
        }

    }
}
