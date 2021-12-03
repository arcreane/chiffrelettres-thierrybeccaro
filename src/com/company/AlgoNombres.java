package com.company;

import java.util.ArrayList;
import java.util.List;

public class AlgoNombres {

    static StringBuilder resolveLogic1 = new StringBuilder();
    static StringBuilder resolveLogic2 = new StringBuilder();
    static StringBuilder resolveLogic3 = new StringBuilder();
    static StringBuilder resolveLogic4 = new StringBuilder();

//    protected static int getResult(List<Integer> myList, int target){
//        //on clone la liste
//        List<Integer> myListClone = myList.stream().collect(Collectors.toList());
//
//        int count =0;
//        List<Integer> myList1 = myList;
//        do {
//            count++;
//            System.out.println(count);
//            //on récupère deux index aléatoire parmi la liste
//            int index = randomNbr(0, myListClone.size()-1);
//            int index2 = randomNbr(0, myListClone.size()-1);
//            //on s'assure que les index soit différents
//            while (index == index2 && myListClone.size() > 1) {
//                index2 = randomNbr(0, myListClone.size()-1);
//            }
//            //opération aléatoire des deux valeurs
//            int x = myListClone.get(index);
//            int y = myListClone.get(index2);
//            int newValue = randomOperation(x, y);
//            //on enlève le couple et on ajoute le résultat de l'opératio
//            myListClone.remove(index);
//            myListClone.remove(index2);
//            myListClone.add(newValue);
//            //on vérifie que y'a po le nombre cible
//            int targetReached = resultCheck(myListClone, target);
//            if (targetReached != 0){
//                System.out.println(targetReached);
//                String found = ("resultat trouvé ! en seulement " + count);
//                System.out.println(found);
//                System.exit(0);
//            }
//        }while (myListClone.size() > 1);
//        getResult(myList1,target);
//        return 0;
//    }

    private static int targetCheck(List<Integer> list, int result) {
        int finalresult = 0;
        for (int k : list) {
            if (k == result) {
                finalresult = k;
            }
        }

        return finalresult;
    }

    protected static int randomNbr(int min, int max) {
        int rand = Main.random.nextInt(min, max);
        return rand;
    }


    private static int Sum(int x, int y) {
        /**
         * Addstwo ints
         * return result
         */
        int sum = x + y;
        return sum;
    }

    private static int Substract(int x, int y) {
        /**
         * Substract two intergers
         * return result
         */
        int sub = 0;
        if (x > y) {
            sub = x - y;
        } else if (y > x) {
            sub = y - x;
        }
        return sub;
    }

    private static int Multi(int x, int y) {
        int multi = x * y;
        return multi;
    }

    private static int Division(int x, int y) {
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


    private static List<Integer> findFirstPairResult(List<Integer> startList) {
        /**
         * Gets All different pairs possbile in a list and calculates a result for every pair for every operation;
         * returns a new list of all pair result
         */
        List<IntPair> pairList = AlgoChiffre.getPairs(startList);
        List<Integer> output = new ArrayList<>();


        for (IntPair nbr : pairList) {
            int sum = AlgoNombres.Sum(nbr.x, nbr.y);
            int sub = AlgoNombres.Substract(nbr.x, nbr.y);
            int div = AlgoNombres.Division(nbr.x, nbr.y);
            int multi = AlgoNombres.Multi(nbr.x, nbr.y);

            output.add(sum);
            output.add(sub);
            output.add(div);
            output.add(multi);
        }
        return output;
    }

    private static List<Integer> findAllResult(List<Integer> startList, List<Integer> outputlist) {
        /**
         * Finds All combination of operations between numbers of two list
         * returns a new output list
         */
        List<Integer> resultOutput = new ArrayList<>();
        for (int j = 0; j < startList.size(); j++) {//on effectue les opérations pour tous les nombres de la nouvelles listes avec les nombres de la première liste
            for (int k = 0; k < outputlist.size(); k++) {
                int startnbr = startList.get(j);
                int outputnbr = outputlist.get(k);
                int sum = AlgoNombres.Sum(startnbr, outputnbr);
                int sub = AlgoNombres.Substract(startnbr, outputnbr);
                int div = AlgoNombres.Division(startnbr, outputnbr);
                int multi = AlgoNombres.Multi(startnbr, outputnbr);

                resultOutput.add(sum);
                resultOutput.add(sub);
                resultOutput.add(div);
                resultOutput.add(multi);

            }
        }
        return resultOutput;
    }

    protected static int findAllSolutions(List<Integer> startList, int target) {
        /**
         * finds all Possible result of basic Operations from numbers of a list
         * and checks if a number matchs the target;
         * if it matches it (or at least should) print the result and the operations used to find
         */
        int i = 0;
        List<Integer> firstPairsResultList = findFirstPairResult(startList);//on choppe les premières paires
        while (i < 5) {
            i++;
            firstPairsResultList = findAllResult(startList, firstPairsResultList);
            int checker = targetCheck(firstPairsResultList, target);
            if (checker != 0) {
                System.out.println("Le Compte Est Bon !");
                return target;
            }
            if(i == 5){
                System.out.println("Impossible de trouver le résultat");
                System.out.println("Recherche de la valeur la plus proche");
                int closest = getApproxValue(firstPairsResultList,target);

                if(closest != 0){
                    System.out.println("la valeur la plus proche est " + closest);
                    return closest;
                }else{
                    System.out.println("pas de chance il était impossible de trouver " + target + " avec " + startList);
                }
            }
        }
        return target;
    }

    private static int getApproxValue(List<Integer> list , int target){
        for( int nbr : list){
            if(nbr == target + 1 || nbr == target - 1){
                return nbr;
            }else if(nbr == target + 2 || nbr == target - 2){
                return nbr;
            }else if(nbr == target + 3 || nbr == target - 3){
                return nbr;
            }else if(nbr == target + 4 || nbr == target - 4){
                return nbr;
            }else if(nbr == target + 5 || nbr == target - 5){
                return nbr;
            }else if(nbr == target + 6 || nbr == target - 6){
                return nbr;
            }else if(nbr == target + 7 || nbr == target - 7){
                return nbr;
            }else if(nbr == target + 8 || nbr == target - 8){
                return nbr;
            }else if(nbr == target + 9 || nbr == target - 9){
                return nbr;
            }else if(nbr == target + 10 || nbr == target - 10){
                return nbr;
            }else{
                nbr = 0;
            }
        }
        int nbr=0;
        return nbr;
    }

}
