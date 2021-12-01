package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class Lettres {

    public void getDICO() throws IOException {
        List<String> dico = new ArrayList<>();
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
        if (choixJoueur.equals("C")){
            int value = Main.random.nextInt(20);
            return consonnes.get(value);
        }
        else if (choixJoueur.equals("V")){
            int value = Main.random.nextInt(6);
            return voyelles.get(value);
        }
        else{
            return "mauvaise entrée";
        }

    }
    public void JeuLettre1V1(){
        System.out.println("bienvenue sur le Mot le plus long");
        List<String> Lettres = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            System.out.println("Joueur 1 voulez vous une consonne ou une voyelle ?");
            String rep = Main.sc.nextLine();
            String value = getLettre(rep);
            if (value.equals("mauvaise entrée")){
                i--;
                System.out.println("veuillez marquer C ou V");
                continue;
            }
            else{
                Lettres.add(value);
                System.out.println("vous avez obtenu la lettre: " + value);
            }
            System.out.println("Joueur 2 voulez vous une consonne ou une voyelle ?");
            rep = Main.sc.nextLine();
            value = getLettre(rep);
            if (value.equals("mauvaise entrée")){
                i--;
                System.out.println("veuillez marquer C ou V");
                continue;
            }
            else{
                Lettres.add(value);
                System.out.println("vous avez obtenu la lettre: " + value);
            }
        }
        System.out.println(Lettres);

    }
}

