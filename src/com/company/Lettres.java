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
    List<String> dico = new ArrayList<>();
    List<String> motPossible = new ArrayList<>();

    public void getDICO() throws IOException {
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

    public String getLettre(String choixJoueur){
        String lettre= null;
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
    public void JeuLettre1V1(){
        System.out.println("Bienvenue sur le Mot le plus long");
        List<String> Lettres = new ArrayList<>();
        List<String> build = new ArrayList<>();
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
            algorythmeLettre(Lettres, dico);
        String mot = motLePlusLong(motPossible);
        System.out.println(mot);

//        extracted(Lettres);
    }

    private void extracted(List<String> Lettres) {
        System.out.println("Voici la liste des lettres disponibles pour la creation du mot");
        System.out.println(Lettres);
        System.out.println("Désormais, Joueur 1 veuillez soumettre votre réponse");
        String rep = Main.sc.nextLine();
        System.out.println("Désormais, Joueur 2 veuillez soumettre votre réponse");
        String rep1 = Main.sc.nextLine();


        if (rep.length() > rep1.length()){
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
        else if (rep1.length() > rep.length()){
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
        else if ((rep.length() == rep1.length())){
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

    }
    private void algorythmeLettre(List<String> lettre,List<String> dico){
      String lettersCombined = String.join("",lettre);
        for (int i = 0; i < dico.size(); i++) {
            TreeMap<Character, Integer> freq = new TreeMap<Character, Integer>();

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
    public String motLePlusLong(List<String> motPossible) {
        String word = "";
        for (String mot: motPossible) {
            if (mot.length() <= 10 && mot.length() > word.length()){
                word = mot;
            }
        }
        return word;
    }
}

