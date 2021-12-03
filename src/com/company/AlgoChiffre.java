package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AlgoChiffre {

    protected static void getNewValues(List<IntPair> pairList){

        }
protected static List<IntPair> getPairs(List<Integer> list){
            List<IntPair> pairs = new ArrayList();
            for(int i = 0; i < list.size()-1; i++){
                for(int j = i+1;j <= list.size()-1 ; j++){
                    IntPair pair = new IntPair(list.get(i),list.get(j));
//                    System.out.println(pair.x + " : " +pair.y);
                    pairs.add(pair);
                }
        }
            return pairs;
    }


// Ici Commence la très grande malédiction, j'ai pété un cable cordialement, krukmak.
    protected static int randomNbr(int min, int max){
        int rand = Main.random.nextInt(min,max);
        return rand;
    }

//    protected static void findResult(List<Integer> list,int result){
//        while (randomSolution(list, result)== 0){
//            randomSolution(list,result);
//        }
//    }
    protected static int randomSolution(List<Integer> list,int result){
        //prend deux int au pif dans la liste a la con et une opération au pif et refou le resultat dans la liste.
        // tant que c'est pas le résultat
        //renvoi le résultat et la suite d'opération
        int count = 0;
        List<Integer> listClone = new ArrayList<>();
        for (int i : list){
            listClone.add(i);
        }
        System.out.println(listClone);
        while(resultCheck(listClone,result) == 0) {
            if(listClone.size() > 1) {
                count++;
                System.out.println(count);
                int index = randomNbr(0, listClone.size()-1);
                int index2 = randomNbr(0, listClone.size()-1);
                while (index == index2 && listClone.size() > 1) {
                    index2 = randomNbr(0, listClone.size()-1);
                }
                int x = listClone.get(index);
                int y = listClone.get(index2);
                int newValue = randomOperation(x, y);
                listClone.remove(index);
                listClone.remove(index2);
                listClone.add(newValue);
                int isResult = resultCheck(listClone, result);
                if (isResult != 0) {
                    System.out.println(isResult);
                    String found = ("resultat trouvé ! en seulement " + count);
                    System.out.println(found);
                    System.exit(0);
                }
            }else{
                break;
            }
        }
        return listClone.get(0);
}

    private static int resultCheck(List<Integer> list,int result) {
        int finalresult = 0;
        for (int k : list){
            if (k == result){
                finalresult = k;
            }
        }
        return finalresult;
    }

    protected static int randomOperation(int x,int y){
        int operation = randomNbr(1,4);
        int result;
        switch(operation){
            case 1 -> result = Sum(x,y);
            case 2 -> result = Substract(x,y);
            case 3 -> result = Multi(x,y);
            case 4 -> result = Division(x,y);
            default -> throw new IllegalStateException("Unexpected value: " + operation);
        }
        return result;
    }

    protected static int Sum(int x,int y){
        int sum = x + y;
        return sum;
    }

    protected static int Substract(int x,int y){
        int sub = 0;
        if (x > y) {
            sub = x - y;
        } else if (y > x) {
            sub = y - x;
        }
        return sub;
    }

    protected static int Multi(int x,int y){
        int multi = x * y;
        return multi;
    }

    protected static int Division(int x,int y){
        int div = 0;
        if (x != 0 && y != 0) {
            if (x > y) {
                div = x / y;
            } else if (y > x) {
                div = y / x;
            }
    }
        return div;
}

}

