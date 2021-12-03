package com.company;

import java.io.IOException;
import java.util.*;


public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void DisplayMenu() throws IOException {
        System.out.println("Bienvenue sur notre super jeu! ^^");
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

        System.out.println("Bye bye!");

    }

    /**
     * Clear Terminal
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
    }


    public static void main(String[] args) throws IOException {
//        DisplayMenu();
        List test = new ArrayList<>();
        test.add(5);
        test.add(2);
        test.add(25);
        test.add(50);
        test.add(75);
        test.add(100);
        int target = random.nextInt(101,999);
        AlgoNombres.findAllSolutions(test,target);

    }


}
