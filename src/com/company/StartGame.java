package com.company;

public class StartGame {

    public void playerVsPlayer(){
        System.out.println("Bonjour bienvenue sur le mode de jeu 1V1 ");
        System.out.println("Veuillez renseigner le nom du joueur 1");
        String nameP1 = Main.sc.nextLine();
        Player Player1 = new Player(nameP1);
        System.out.println("Veuillez renseigner le nom du joueur 2");
        String nameP2 = Main.sc.nextLine();
        Player Player2 = new Player(nameP2);
    }

    public void playerVsIa(){
        System.out.println("Bonjour bienvenue sur le mode de jeu joueur contre IA ");
        System.out.println("Veuillez renseigner votre nom");
        String nameP = Main.sc.nextLine();
        Player Player = new Player(nameP);
    }

}
