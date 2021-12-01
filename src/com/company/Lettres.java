package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    public String GetLettre(){
        String lettre= null;
        List<String> voyelles = new ArrayList<>();
        List<String> consonnes = new ArrayList<>();

        Random rand = new Random();
        char c = (char)(rand.nextInt(26) + 97);


        return lettre;
    }
    public void JeuLettre(){
        System.out.println("bienvenue sur le Mot le plus long");

    }
}

