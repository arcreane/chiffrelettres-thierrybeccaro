package com.company;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void DisplayMenu(){
        System.out.println("Bienvenue sur notre super jeu! ^^");
        Scanner sc = new Scanner(System.in);

        boolean repeat = true;
        while (repeat){
            Game.waitAWhile(2);
            clearScreen();
            try{

                System.out.println("1) Mode 1 joueur (contre ia) \n2) Mode 2 joueurs \n3) Scores \n4) Quitter");
                int choice = sc.nextInt();
                switch (choice) {

                    case 1 -> StartGame.playerVsIa();
                    case 2 -> StartGame.playerVsPlayer();
                    case 3 -> System.out.println("Voici les Scores");
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


    public static void main(String[] args) {

        DisplayMenu();
    }


}
