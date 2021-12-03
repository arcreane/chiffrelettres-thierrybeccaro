package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Chiffres {

    protected static List nbrSelect(Player player1, Player player){
//        Gets rank choice from each player and returns a list of random "plaquettes"
        ArrayList<Integer> rank1 = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10));
        ArrayList<Integer> rank2 = new ArrayList<>(Arrays.asList(25, 25, 50, 50));
        ArrayList<Integer> rank3 = new ArrayList<>(Arrays.asList(75, 75, 100, 100));
        List<Integer> result = new ArrayList();
        int count = 0;
        while(count  < 3){
            playerChoice(player1,rank1,rank2,rank3,result);
            playerChoice(player,rank1,rank2,rank3,result);
            count++;
        }
        //System.out.println(result);
        return result;

    }
     protected static List nbrSelectVsComputer(Player player, Player computer){
        // Gets rank choice from player and a random one from computer
         ArrayList<Integer> rank1 = new ArrayList<>(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10));
         ArrayList<Integer> rank2 = new ArrayList<>(Arrays.asList(25, 25, 50, 50));
         ArrayList<Integer> rank3 = new ArrayList<>(Arrays.asList(75, 75, 100, 100));
         List<Integer> result = new ArrayList();
         int count = 0;
         while(count  < 3){
             if(rank3.size()== 0){
                 System.out.println("Le rang 3 est épuisé veuillez en choisir un autre");
             }else if(rank2.size()== 0){
                 System.out.println("Le rang  2 est épuisé veuillez en choisir un autre");
             }
             playerChoice(player,rank1,rank2,rank3,result);
             computerChoice(computer,rank1,rank2,rank3,result);
             count++;
         }
         //System.out.println(result);
         return result;
     }

    protected static void getNumber( ArrayList<Integer> list, List output){
        int min = 1;
        int max = list.size()-1;
        int i=0;
        int result = 0;

        while(i < 4){
            int index = Main.random.nextInt(max - min) + min;
            if(list.get(index) != null){
                result = list.get(index);
                list.set(index, null);
                break;
            }
            else if(i == 3){
                result = Main.random.nextInt(1,10);
            }
            else{
                i++;
            }
        }
        System.out.println(result);
        output.add(result);
    }
    protected static void computerChoice(Player player, ArrayList rang1, ArrayList rang2, ArrayList rang3, List result) {
        int min = 1;
        int max = 3;
        int listChoice = Main.random.nextInt(max - min + 1) + min;
        switch (listChoice) {
            case 1 -> getNumber(rang1, result);
            case 2 -> getNumber(rang2, result);
            case 3 -> getNumber(rang3, result);
        }
    }

    protected static void playerChoice(Player player, ArrayList rang1, ArrayList rang2, ArrayList rang3, List result){
        //
        System.out.println(player.name + " Veuillez choisir un rang,\nRangs disponibles : Rang 1 :chiffres de 1 à 10, Rang 2 : 25 ou 50, Rang 3 : 50 ou 100.");
        int choice = Main.sc.nextInt();
        switch (choice) {
            case 1 -> getNumber(rang1, result);
            case 2 -> getNumber(rang2, result);
            case 3 -> getNumber(rang3, result);
        }
    }

    protected static void answerCheck(String answer,int target){
        int playeranswer = Integer.parseInt(answer);
        if (playeranswer == target){
            System.out.println("vous avez réussi, le compte est bon!");
        }else{
            System.out.println("oulà le compte est pas bon mon pote");
        }
    }


}
