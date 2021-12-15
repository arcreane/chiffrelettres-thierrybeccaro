package com.company;

import java.io.*;
import java.util.*;

public class Highscores {

    static public Hashtable<String, ArrayList<String>> scoresBoard = new Hashtable<>();

    public static void writeNamesAndScores(Player Player1, Player Player2){
        String key = Player1.name + " VS "+Player2.name;
        ArrayList<String> values = new ArrayList<>();
        if(scoresBoard.containsKey(key)){
            StringBuilder iValues = new StringBuilder(String.valueOf(scoresBoard.get(key)));
            iValues.delete(0, 1);
            iValues.setLength(iValues.length() - 1);
            values.add(String.valueOf(iValues));
        }
        values.add(Player1.score+" - "+Player2.score);
        scoresBoard.put(key, values);
    }

    public static void displayHighScores() throws Exception {
        Main.clearScreen();
        Main.artGen.printTextArt("SCORES",
                ASCIIArtGenerator.ART_SIZE_MEDIUM,
                ASCIIArtGenerator.ASCIIArtFont.ART_FONT_SANS_SERIF,
                "░");
        System.out.println();
        printScores();
        Game.waitAWhile(5);
    }

    /**
     * Prints the data of scores.
     */
    public static void printScores() {
        scoresBoard.forEach(
                (k, v) -> System.out.println("JOUEURS : " + k + "\t///\t"+ "SCORES : " + v));
    }


    /**
     * Searches for fileName.txt file and reads it line by line
     * @param fileName name of the backup file
     */
    public static void getSavedScore(String fileName) throws IOException {
        Hashtable<String, ArrayList<String>> input = new Hashtable<>();
        //Create a text file if it doesn't already exist
        File file = new File(fileName);
        file.createNewFile(); // if file already exists will do nothing
        FileOutputStream oFile = new FileOutputStream(file, true);

        // Get the absolute path of file
        String absolute = file.getAbsolutePath();

        try {
            FileReader reader = new FileReader(absolute);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\t///\t");
                String key = data[0];
                ArrayList<String> values = new ArrayList<>();
                StringBuilder iValues = new StringBuilder(String.valueOf(data[1]));
                iValues.delete(0, 1);
                iValues.setLength(iValues.length() - 1);
                values.add(String.valueOf(iValues));
                scoresBoard.put(key, values);
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets FileName.txt and rewrites it with the new scores.
     * @param fileName name of the backup file
     */
    public static void writeScores(String fileName){
        File file = new File(fileName);
        String absolute = file.getAbsolutePath();
        try {
            FileWriter writer = new FileWriter(absolute, false); // true to append
            // false to overwrite.
            scoresBoard.forEach(
                    (k, v) -> {
                        try {
                            writer.write(k + "\t///\t" + v+ "\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

