package com.company;

import java.io.IOException;

public class StartGame {

    public static void playerVsPlayer() throws IOException {
        System.out.println("Bonjour bienvenue sur le mode de jeu 1V1 ");
        System.out.println("Veuillez renseigner le nom du joueur 1");
        String nameP1 = Main.sc.nextLine();
        Player Player1 = new Player(nameP1);
        System.out.println("Veuillez renseigner le nom du joueur 2");
        String nameP2 = Main.sc.nextLine();
        Player Player2 = new Player(nameP2);

        //Beginning of the Game
        Game.round("1V1");

        //End of the Game

    }

    public static void playerVsIa() throws IOException {
        System.out.println("Bonjour bienvenue sur le mode de jeu joueur contre IA ");
        System.out.println("Veuillez renseigner votre nom");
        String nameP = Main.sc.nextLine();
        Player Player = new Player(nameP);

        //Beginning of the Game
        Game.round("1Via");

        //EndGame

    }

}
