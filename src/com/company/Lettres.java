package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Lettres {
    static List<String> dico = new ArrayList<>();
    static List<String> motPossible = new ArrayList<>();

    public static void getDICO() throws IOException {
        /**
         * function that open the dictionnary and put every word in a list
         */
        dico.clear();
        BufferedReader lecteurAvecBuffer = null;
        String ligne;
        try
        {
            lecteurAvecBuffer = new BufferedReader(new FileReader("liste_francais.txt", StandardCharsets.ISO_8859_1));
        }
        catch(FileNotFoundException exc)
        {
            System.out.println("Erreur d'ouverture");
        }
        while ((ligne = lecteurAvecBuffer.readLine()) != null)
            dico.add(ligne);
        lecteurAvecBuffer.close();
    }

    public static String getLettre(String choixJoueur){
        /**
         * function that give a random letter of the type the player requested
         * arguments : take the player choice in argument
         * return a letter depending on the  choice of the user
         */
        List<String> voyelles = new ArrayList<>();
        List<String> consonnes = new ArrayList<>();

        for (char c = 'a'; c <= 'z';c++) {
            String value = String.valueOf(c);
            if (value.equals("a") || value.equals("e") || value.equals("i") || value.equals("o") || value.equals("u") || value.equals("y")) {
                voyelles.add(value);
            } else {
                consonnes.add(value);
            }
        }
        if (choixJoueur.equals("c")){
            int value = Main.random.nextInt(20);
            return consonnes.get(value);
        }
        else if (choixJoueur.equals("v")){
            int value = Main.random.nextInt(6);
            return voyelles.get(value);
        }
        else{
            return "mauvaise entrée";
        }
    }

    public static void JeuLettre1V1(Player player1, Player player2) throws IOException {
        /**
         * function that create the game of letter for player vs player
         * argument : the object of the two player
         */
        Main.clearScreen();
        System.out.println("Bienvenue sur le Mot le plus long");
        List<String> Lettres = new ArrayList<>();
        try {
            getDICO();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println( player1.name + " voulez vous une consonne ou une voyelle ?");
            String rep = Main.sc.nextLine();
            String value = getLettre(rep);
            if (value.equals("mauvaise entrée")){
                i--;
                System.out.println("Veuillez marquer c ou v");
                continue;
            }
            else{
                Lettres.add(value);
                System.out.println("Vous avez obtenu la lettre: " + value.toUpperCase());
            }
            System.out.println(player2.name + " voulez vous une consonne ou une voyelle ?");
            rep = Main.sc.nextLine();
            value = getLettre(rep);
            if (value.equals("mauvaise entrée")){
                Lettres.remove(i);
                i--;
                System.out.println("Veuillez marquer c ou v");
                continue;
            }
            else{
                Lettres.add(value);
                System.out.println("Vous avez obtenu la lettre: " + value.toUpperCase());
            }
        }
        input_user(Lettres,player1,player2);
        algorythmeLettre(Lettres, dico);
        String mot = motLePlusLong(motPossible, Lettres);
        System.out.println("voici le plus long mot qui était possible: " + mot);
    }

    public static void JeuLettre1vsIA(Player player, Player computer) throws IOException {
        /**
         * function that create the game of letter for player vs computer
         * argument : the object of the two player
         */
        Main.clearScreen();
        System.out.println("Bienvenue sur le Mot le plus long");
        List<String> Lettres = new ArrayList<>();

        try {
            getDICO();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(player.name + ", voulez vous une consonne ou une voyelle ?");
            String rep = Main.sc.nextLine();
            String value = getLettre(rep);
            if (value.equals("Mauvaise entrée")){
                i--;
                System.out.println("Veuillez marquer C ou V");
                continue;
            }
            else{
                Lettres.add(value);
                System.out.println("Vous avez obtenu la lettre: " + value.toUpperCase());
            }
            int rdm = Main.random.nextInt(3);
            if (rdm == 1){
                rep = "v";
                System.out.println("L'ordinateur a pioché une voyelle");
            }
            else if (rdm == 2){
                rep = "c";
                System.out.println("L'ordinateur a pioché une consonne");
            }
            value = getLettre(rep);
            Lettres.add(value);
            System.out.println("L'ordinateur a obenu la lettre: " + value.toUpperCase());
        }
        extracted1(Lettres, player, computer);
        String mot = motLePlusLong(motPossible, Lettres);
        System.out.println("voici le plus long mot qui était possible: " + mot);
    }

    private static void extracted1(List<String> Lettres, Player player, Player computer) throws IOException {
        /**
         * function extracted from the player vs computer, it permit to take the input of the player and compare it to the computer
         * arguments : take the list of letters as an argument for verification
         * argument : take the object of the player and computer
         */
        Main.clearScreen();
        System.out.println("Voici la liste des lettres disponibles pour la création du mot :");

        //Big print letters to associate
        String text = String.join(" ", Lettres);
        System.out.println();
        Main.artGen.printTextArt(text.toUpperCase(),
                ASCIIArtGenerator.ART_SIZE_MEDIUM,
                ASCIIArtGenerator.ASCIIArtFont.ART_FONT_SANS_SERIF,
                "░");
        System.out.println();
        //Beginning of the Timer
        Timer.DisplayTimer("Il vous reste", 60);
        System.out.println(player.name + ", veuillez soumettre votre réponse");
        String rep = Player.getPlayerAnswer(30);
        algorythmeLettre(Lettres, dico);
        int rdm = Main.random.nextInt(motPossible.size());
        String rep1 = motPossible.get(rdm);
        System.out.println("Voici le mot de l'ordinateur: " + rep1);

        if (rep.length() > rep1.length()){
            if(verifMot(rep, Lettres)){
                if (dico.contains(rep)){
                    System.out.println("bravo ! " + player.name + " vous avez donné le mot le plus long");
                    player.setScoreLettre(rep);
                    System.out.println("vous gagnez donc " + rep.length() + " points");
                }
                else{
                    System.out.println(player.name + " votre mot n'existe pas !");
                    if (dico.contains(rep1)){
                        System.out.println("Dommage " + player.name + "votre mot n'existe pas, la victoire reviens donc à l'odinateur");
                    }
                }
            }
            else{
                System.out.println("Bah alors" + player.name +", on utilise des lettres non autorisées ? petit coquin va");
            }
        }
        else if (rep1.length() > rep.length()){
            if (verifMot(rep1, Lettres)){
                if (dico.contains(rep1)){
                    System.out.println("Dommage " + player.name + ", c'est l'ordinateur qui remporte la victoire");
                }
            }
        }
        else if ((rep.length() == rep1.length())){
            if(verifMot(rep, Lettres) && verifMot(rep1, Lettres)){
                if (dico.contains(rep) && dico.contains(rep1)){
                    System.out.println("Cher Joueur c'est une égalité !");
                    player.setScoreLettre(rep);
                    System.out.println("vous gagnez donc " + rep.length() + " points");
                    computer.setScoreLettre(rep1);

                }
                else if (dico.contains(rep1) && !dico.contains(rep)){
                    System.out.println("Dommage " + player.name + "votre mot n'existe pas la victoire revient donc à l'ordinateur");
                }
            }
            else if(verifMot(rep1, Lettres) && !verifMot(rep, Lettres)){
                System.out.println(player.name + "votre mot n'est pas valide car il utilise des lettres non autorisés, par conséquent la victoire revient a l'ordinateur");
            }

        }
    }

    private static void input_user(List<String> Lettres, Player player1, Player player2) throws Exception {
        /**
         * function extracted from the player vs computer, it permit to take the input of the player and compare it to the computer
         * arguments : take the list of letters as an argument for verification
         * argument : take the object of the players
         */
        Main.clearScreen();
        System.out.println("Voici la liste des lettres disponibles pour la création du mot");
        //Big print letters to associate
        String text = String.join(" ", Lettres);
        System.out.println();
        Main.artGen.printTextArt(text.toUpperCase(),
                ASCIIArtGenerator.ART_SIZE_MEDIUM,
                ASCIIArtGenerator.ASCIIArtFont.ART_FONT_SANS_SERIF,
                "░");
        System.out.println();
        //Beginning of the Timer
        Timer.DisplayTimer("Il vous reste ", 60);
        //
        System.out.println(player1.name + ", veuillez soumettre votre réponse");
        String rep = Player.getPlayerAnswer(30);
        System.out.println(player2.name + ", veuillez soumettre votre réponse");
        String rep1 = Player.getPlayerAnswer(30);


        if (rep.length() > rep1.length()){
            if(verifMot(rep, Lettres)){
                informPlayer(player1, player2, rep, rep1);
            }
            else{
                System.out.println("Bah alors, " +player1.name + ", on utilise des lettres non autorisées ? Petit coquin va !");
            }
        }
        else if (rep1.length() > rep.length()){
            if (verifMot(rep1, Lettres)){
                informPlayer(player2, player1, rep1, rep);

//                if (dico.contains(rep1)){
//                    System.out.println("Bravo, " + player2.name + "! Vous avez donné le mot le plus long");
//                    player2.setScoreLettre(rep1);
//                    System.out.println("vous gagnez donc " + rep1.length() + " points");
//                }
//                else {
//                    System.out.println(player2.name + ", votre mot n'existe pas !");
//                    if (dico.contains(rep)) {
//                        System.out.println("Bravo, " + player1.name + "! vous avez donné le mot le plus long");
//                        player1.setScoreLettre(rep);
//                        System.out.println("Vous gagnez donc " + rep.length() + " points");
//                    } else {
//                        System.out.println(player1.name + ", votre mot n'existe pas !");
//                    }
//                }
            }
            else{
                System.out.println("Bah alors, " + player2.name +", on utilise des lettres non autorisées ? Petit coquin va !");
            }
        }
        else if ((rep.length() == rep1.length())){
            if(verifMot(rep, Lettres) && verifMot(rep1, Lettres)){
                if (dico.contains(rep) && dico.contains(rep1)){
                    System.out.println("Cher joueur c'est une égalité !");
                    player1.setScoreLettre(rep);
                    System.out.println("Vous gagnez donc " + rep.length() + " points");
                    player2.setScoreLettre(rep1);

                }
                else if (dico.contains(rep) && !dico.contains(rep1)){
                    System.out.println("Bravo " +  player1.name + "vous gagnez cette manche, contrairement à" + player2.name + " votre mot existe !");
                    player1.setScoreLettre(rep);
                    System.out.println("Vous gagnez donc " + rep.length() + " points");
                }
                else if (dico.contains(rep1) && !dico.contains(rep)){
                    System.out.println("Bravo " +  player2.name + "vous gagnez cette manche, contrairement à" + player2.name + " votre mot existe !");
                    player2.setScoreLettre(rep1);
                    System.out.println("Vous gagnez donc " + rep1.length() + " points");
                }
            }
            else if(verifMot(rep, Lettres) && !verifMot(rep1, Lettres)){
                System.out.println("le mot de " + player2.name + "n'est pas valide car il utilise des lettres non autorisées, par conséquent la victoire revient à " + player1.name);
                player2.setScoreLettre(rep);
                System.out.println("Vous gagnez donc " + rep.length() + " points");
            }
            else if(verifMot(rep1, Lettres) && !verifMot(rep, Lettres)){
                System.out.println("Le mot de " + player1.name + "n'est pas valide car il utilise des lettres non autorisées, par conséquent la victoire revient à " + player2.name);
                player1.setScoreLettre(rep1);
                System.out.println("Vous gagnez donc " + rep1.length() + " points");
            }

        }

    }

    private static void informPlayer(Player player1, Player player2, String rep, String rep1) {

        if (dico.contains(rep)){
            setPlayerScore(player1, rep);
        }
        else{
            System.out.println(player1.name + ", votre mot n'existe pas !");
            if (dico.contains(rep1)){
                setPlayerScore(player2,rep1);
//                System.out.println("Bravo, " + player2.name + " ! Vous avez donné le mot le plus long");
//                player2.setScoreLettre(rep1);
//                System.out.println("Vous gagnez donc " + rep1.length() + " points");
            }
            else{
                System.out.println(player2.name + ", votre mot n'existe pas !");
            }
        }
    }

    private static void setPlayerScore(Player player, String rep) {
        System.out.println("Bravo, " + player.name + " ! Vous avez donné le mot le plus long");
        player.setScoreLettre(rep);
        System.out.println("Vous gagnez donc " + rep.length() + " points");
    }

    private static void algorythmeLettre(List<String> lettre, List<String> dico){
        /**
         * function that find all the word possible in the dictionnary with the set of letter given
         * arguments : the list of letters available and the dictionnary
         * create a list of all the possible word
         */

        motPossible.clear();
      String lettersCombined = String.join("",lettre);
      boolean wordValid = true;
        for (int i = 0; i < dico.size(); i++) {
            TreeMap<Character, Integer> freq = new TreeMap<>();

            for (int j = 0; j < dico.get(i).length(); j++){
                freq.put(dico.get(i).charAt(j), 0);
            }

            for (int j = 0; j < lettersCombined.length(); j++) {
                if (freq.containsKey(lettersCombined.charAt(j)))
                    freq.put(lettersCombined.charAt(j), freq.get(lettersCombined.charAt(j)) + 1);
            }

            boolean match = true;

            for (int count : freq.values()) {
                if (count <= 0) {
                    match = false;
                    break;
                }
            }
            if (match){
                motPossible.add(dico.get(i));
            }
        }
    }

    public static String motLePlusLong(List<String> motPossible,List<String> lettres) {
        /**
         * funtion that determine wich word of all the possible word is the longest and remove every word that use many times a letter that is given once
         * arguments : the list of possible word and the list of available letters
         * return the longest word
         */
        String wordRef = "";

        for (String mot : dico){
            boolean wordIsValid = true;
            ArrayList<String> cloneLetters = new ArrayList<>();
            for (int k = 0; k < lettres.size(); k++){
                cloneLetters.add(lettres.get(k));
            }
            String[] wordSplit = mot.split("");
            for (int i = 0; i < mot.length(); i++) {
                if (wordIsValid) {
                    for (int j = 0; j < cloneLetters.size(); j++) {
                        if (Objects.equals(wordSplit[i], cloneLetters.get(j))) {
                            wordIsValid = true;
                            cloneLetters.remove(j);
                            break;
                        } else {
                            wordIsValid = false;
                        }
                    }
                }
            }
            if(wordIsValid && mot.length() <= 10 && mot.length() > wordRef.length()){
                wordRef = mot;
            }
            else{
                motPossible.remove(mot);
            }
        }
        return wordRef;

    }

    public static boolean verifMot(String mot, List<String> lettres){
        /**
         * function that verify if the word the user has given use the given letter
         * Arguments : the user's word and the list of available letters
         * return a true or false depending on the validity of the word
         */
        boolean verif = false;
        String word = mot;
        String lettersCombined = String.join("",lettres);

        for (int i = 0; i < lettres.size(); i++) {
            char lettre = lettersCombined.charAt(i);
            for (int j = 0; j < mot.length(); j++) {
                if (lettre == mot.charAt(j)){
                    String letter = String.valueOf(mot.charAt(j));
                    word = word.replace(letter, "");
                }
            }
        }
        if (word.equals("")){
            verif = true;
        }
        return verif;
    }

}

