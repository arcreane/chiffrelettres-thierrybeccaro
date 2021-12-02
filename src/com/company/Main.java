package com.company;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void DisplayMenu(){
        System.out.println("Bienvenue sur notre super jeu ^^");
        Scanner sc = new Scanner(System.in);

        boolean repeat = true;
        while (repeat){
            try{
                System.out.println("1) Mode 1 joueur (contre ia) \n2) Mode 2 joueurs \n3) Quitter");
                int choix = sc.nextInt();
                switch (choix) {

                    case 1 -> StartGame.playerVsIa();
                    case 2 -> StartGame.playerVsPlayer();
                    case 3 -> repeat = false;
                    default -> System.err.println ( "Unrecognized option" );

                }
            }
            catch (InputMismatchException | IOException e){
                System.err.println("input non valide");
                sc.nextLine();
            }
        }

    }

    public static void main(String[] args) {
        DisplayMenu();
    }
}
