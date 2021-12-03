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
                System.out.println("Vous avez obtenu la lettre: " + value);
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
                System.out.println("Vous avez obtenu la lettre: " + value);
            }
        }
        extracted(Lettres,player1,player2);
        algorythmeLettre(Lettres, dico);
        String mot = motLePlusLong(motPossible, Lettres);
        System.out.println("voici le plus long mot qui était possible: " + mot);
    }
    public static void JeuLettre1vsIA(Player player) throws IOException {
        Main.clearScreen();
        System.out.println("Bienvenue sur le Mot le plus long");
        List<String> Lettres = new ArrayList<>();

        try {
            getDICO();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(player.name + " voulez vous une consonne ou une voyelle ?");
            String rep = Main.sc.nextLine();
            String value = getLettre(rep);
            if (value.equals("mauvaise entrée")){
                i--;
                System.out.println("Veuillez marquer C ou V");
                continue;
            }
            else{
                Lettres.add(value);
                System.out.println("Vous avez obtenu la lettre: " + value);
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
            System.out.println("L'ordinateur a obenu la lettre: " + value);
        }
        extracted1(Lettres, player);
        String mot = motLePlusLong(motPossible, Lettres);
        System.out.println("voici le plus long mot qui était possible: " + mot);
    }

    private static void extracted1(List<String> Lettres, Player player) throws IOException {
        Main.clearScreen();
        System.out.println("Voici la liste des lettres disponibles pour la creation du mot");
        System.out.println(Lettres);
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

    private static void extracted(List<String> Lettres, Player player1, Player player2) throws IOException {
        Main.clearScreen();
        System.out.println("Voici la liste des lettres disponibles pour la creation du mot");
        System.out.println(Lettres);
        Timer.DisplayTimer("Il vous reste", 60);
        System.out.println(player1.name + ", veuillez soumettre votre réponse");
        String rep = Player.getPlayerAnswer(30);
        System.out.println(player2.name + ", veuillez soumettre votre réponse");
        String rep1 = Player.getPlayerAnswer(30);


        if (rep.length() > rep1.length()){
            if(verifMot(rep, Lettres)){
                if (dico.contains(rep)){
                    System.out.println("bravo ! " + player1.name + " vous avez donné le mot le plus long");
                }
                else{
                    System.out.println(player1.name + " votre mot n'existe pas !");
                    if (dico.contains(rep1)){
                        System.out.println("bravo ! " + player2.name + " vous avez donné le mot le plus long");
                    }
                    else{
                        System.out.println(player2.name + " votre mot n'existe pas !");
                    }
                }
            }
            else{
                System.out.println("Bah alors" +player1.name + ", on utilise des lettres non autorisées ? petit coquin va");
            }
        }
        else if (rep1.length() > rep.length()){
            if (verifMot(rep1, Lettres)){
                if (dico.contains(rep1)){
                    System.out.println("bravo ! " + player2.name + " vous avez donné le mot le plus long");
                }
                else {
                    System.out.println(player2.name + " votre mot n'existe pas !");
                    if (dico.contains(rep)) {
                        System.out.println("bravo ! " + player1.name + " vous avez donné le mot le plus long");
                    } else {
                        System.out.println(player1.name + " votre mot n'existe pas !");
                    }
                }
            }
            else{
                System.out.println("Bah alors " + player2.name +", on utilise des lettres non autorisées ? petit coquin va");
            }
        }
        else if ((rep.length() == rep1.length())){
            if(verifMot(rep, Lettres) && verifMot(rep1, Lettres)){
                if (dico.contains(rep) && dico.contains(rep1)){
                    System.out.println("Cher joueur c'est une égalité !");
                }
                else if (dico.contains(rep) && !dico.contains(rep1)){
                    System.out.println("Bravo " +  player1.name + "vous gagnez cette manche, contrairement à" + player2.name + " votre mot existe !");
                }
                else if (dico.contains(rep1) && !dico.contains(rep)){
                    System.out.println("Bravo " +  player2.name + "vous gagnez cette manche, contrairement à" + player2.name + " votre mot existe !");
                }
            }
            else if(verifMot(rep, Lettres) && !verifMot(rep1, Lettres)){
                System.out.println("le mot de " + player2.name + "n'est pas valide car il utilise des lettres non autorisés, par conséquent la victoire revient à " + player1.name);
            }
            else if(verifMot(rep1, Lettres) && !verifMot(rep, Lettres)){
                System.out.println("le mot de " + player1.name + "n'est pas valide car il utilise des lettres non autorisés, par conséquent la victoire revient à " + player2.name);
            }

        }

    }

    private static void algorythmeLettre(List<String> lettre, List<String> dico){
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

