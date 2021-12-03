package com.company;

import java.io.IOException;
//import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();
    public static ASCIIArtGenerator artGen = new ASCIIArtGenerator();

    public static void DisplayMenu() throws Exception {
        System.out.println();
        artGen.printTextArt("Bienvenue",
                ASCIIArtGenerator.ART_SIZE_MEDIUM,
                ASCIIArtGenerator.ASCIIArtFont.ART_FONT_SANS_SERIF,
                "░");
        System.out.println();
        System.out.println("sur notre super jeu! ^^");
        Game.waitAWhile(2);

        Scanner sc = new Scanner(System.in);
        //Get saved Scores
        Highscores.getSavedScore("CLScores.txt");
        boolean repeat = true;
        while (repeat){
            Game.waitAWhile(1);
            clearScreen();
            try{

                System.out.println("1) Mode 1 joueur (contre ia) \n2) Mode 2 joueurs \n3) Scores \n4) Quitter");
                int choice = sc.nextInt();
                switch (choice) {

                    case 1 -> StartGame.playerVsIa();
                    case 2 -> StartGame.playerVsPlayer();
                    case 3 -> Highscores.displayHighScores();
                    case 4 -> repeat = false;
                    default -> System.err.println ( "Option non reconnue" );

                }
            }
            catch (InputMismatchException | IOException e){
                System.err.println("Input non valide");
                sc.nextLine();
            }
        }

        System.out.println();
        artGen.printTextArt("Au revoir !",
                ASCIIArtGenerator.ART_SIZE_MEDIUM,
                ASCIIArtGenerator.ASCIIArtFont.ART_FONT_SANS_SERIF,
                "░");
        System.out.println();

    }

    /**
     * Clear Terminal
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }


    public static void main(String[] args) throws Exception {

        DisplayMenu();
    }


}
