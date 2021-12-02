package com.company;

import java.io.IOException;
import java.util.*;

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

                    case 1 -> System.out.println("Mode 1 joueur");
                    case 2 -> System.out.println("Mode 2 joueurs");
                    case 3 -> repeat = false;
                    default -> System.err.println ( "Unrecognized option" );

                }
            }
            catch (InputMismatchException e){
                System.err.println("input non valide");
                sc.nextLine();
            }
        }

    }

    public static void main(String[] args) {
//        Player Test = new Player("Test");
//        Player Bot = new Player("Bot");
//        Chiffres.nbrSelectVsComputer(Test,Bot);
        List test = new ArrayList();
        test.add(7);
        test.add(100);
        test.add(50);
        test.add(75);
        test.add(3);
        test.add(25);
        AlgoChiffre.getPairs(test);
    }
}
