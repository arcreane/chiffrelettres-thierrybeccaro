package com.company;

import java.util.ArrayList;
import java.util.List;

public class AlgoNombres {

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
        int sum = x + y;
        return sum;
    }

    private static int Substract(int x, int y) {
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

    protected static void findAllSolutions(List<Integer> startList, int target) {
        int i = 0;
        List<Integer> firstPairsResultList = findFirstPairResult(startList);//on choppe les premières paires
        while (i < 5) {
            i++;
            firstPairsResultList = findAllResult(startList, firstPairsResultList);
            int checker = targetCheck(firstPairsResultList, target);
            if (checker != 0) {

                System.out.println("résultat trouvé");
                break;
            }
            if(i == 5){
                System.out.println("Impossible de trouver le résultat");
            }
        }
    }

}
