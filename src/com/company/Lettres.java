package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


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
    public static void JeuLettre1V1() throws IOException {
        Main.clearScreen();
        System.out.println("Bienvenue sur le Mot le plus long");
        List<String> Lettres = new ArrayList<>();
        try {
            getDICO();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("Joueur 1 voulez vous une consonne ou une voyelle ?");
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
            System.out.println("Joueur 2 voulez vous une consonne ou une voyelle ?");
            rep = Main.sc.nextLine();
            value = getLettre(rep);
            if (value.equals("mauvaise entrée")){
                i--;
                System.out.println("Veuillez marquer C ou V");
                continue;
            }
            else{
                Lettres.add(value);
                System.out.println("Vous avez obtenu la lettre: " + value);
            }
        }
        extracted(Lettres);
        algorythmeLettre(Lettres, dico);
        String mot = motLePlusLong(motPossible);
        System.out.println("voici le plus long mot qui était possible: " + mot);
    }

    private static void extracted(List<String> Lettres) throws IOException {
        Main.clearScreen();
        System.out.println("Voici la liste des lettres disponibles pour la creation du mot");
        System.out.println(Lettres);
        Timer.DisplayTimer("Il vous reste", 60);
        System.out.println("Désormais, Joueur 1 veuillez soumettre votre réponse");
        String rep = Player.getPlayerAnswer(30);
        System.out.println("Désormais, Joueur 2 veuillez soumettre votre réponse");
        String rep1 = Player.getPlayerAnswer(30);


        if (rep.length() > rep1.length()){
            if(verifMot(rep, Lettres)){
                if (dico.contains(rep)){
                    System.out.println("Bravo joueur 1, vous avez donné le mot le plus long");
                }
                else{
                    System.out.println("Joueur 1 votre mot n'existe pas !");
                    if (dico.contains(rep1)){
                        System.out.println("Bravo joueur 2, vous avez donné le mot le plus long");
                    }
                    else{
                        System.out.println("Joueur 2 votre mot n'existe pas !");
                    }
                }
            }
            else{
                System.out.println("Bah alors Joueur 1, on utilise des lettres non autorisées ? petit coquin va");
            }
        }
        else if (rep1.length() > rep.length()){
            if (verifMot(rep1, Lettres)){
                if (dico.contains(rep1)){
                    System.out.println("Bravo joueur 2, vous avez donné le mot le plus long");
                }
                else {
                    System.out.println("Joueur 2 votre mot n'existe pas !");
                    if (dico.contains(rep)) {
                        System.out.println("Bravo joueur 1, vous avez donné le mot le plus long");
                    } else {
                        System.out.println("Joueur 1 votre mot n'existe pas !");
                    }
                }
            }
            else{
                System.out.println("Bah alors Joueur 2, on utilise des lettres non autorisées ? petit coquin va");
            }
        }
        else if ((rep.length() == rep1.length())){
            if(verifMot(rep, Lettres) && verifMot(rep1, Lettres)){
                if (dico.contains(rep) && dico.contains(rep1)){
                    System.out.println("Cher joueur c'est une égalité !");
                }
                else if (dico.contains(rep) && !dico.contains(rep1)){
                    System.out.println("Bravo joueur 1 vous gagnez cette manche, contrairement au joueur 2 votre mot existe !");
                }
                else if (dico.contains(rep1) && !dico.contains(rep)){
                    System.out.println("Bravo joueur 2 vous gagnez cette manche, contrairement au joueur 1 votre mot existe !");
                }
            }
            else if(verifMot(rep, Lettres) && !verifMot(rep1, Lettres)){
                System.out.println("le mot de Joueur 2 n'est pas valide car il utilise des lettres non autorisés, par conséquent la victoire revient au joueur 1");
            }
            else if(verifMot(rep1, Lettres) && !verifMot(rep, Lettres)){
                System.out.println("le mot de Joueur 1 n'est pas valide car il utilise des lettres non autorisés, par conséquent la victoire revient au joueur 2");
            }

        }

    }
    private static void algorythmeLettre(List<String> lettre, List<String> dico){
      String lettersCombined = String.join("",lettre);
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
    public static String motLePlusLong(List<String> motPossible) {
        String word = "";
        for (String mot: motPossible) {
            if (mot.length() <= 10 && mot.length() > word.length()){
                word = mot;
            }
        }
        return word;
    }

    public boolean verifMot(String mot, List<String> lettres){
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

