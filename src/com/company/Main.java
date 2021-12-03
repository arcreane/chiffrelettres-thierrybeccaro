package com.company;

import java.util.*;

public class Main {

    public static Scanner sc = new Scanner(System.in);
    public static Random random = new Random();

    public static void DisplayMenu(){
        System.out.println("Bienvenue sur notre super jeu ^^");
        Scanner sc = new Scanner(System.in);

        boolean repeat = true;
        while (repeat){
            try{
                System.out.println("1) Mode 1 joueur (contre ia) \n2) Mode 2 joueurs \n3) Quitter");
                int choix = sc.nextInt();
                switch (choix) {

                    case 1 -> System.out.println("Mode 1 joueur");
                    case 2 -> System.out.println("Mode 2 joueurs");
                    case 3 -> repeat = false;
                    default -> System.err.println ( "Unrecognized option" );

                }
            }
            catch (InputMismatchException e){
                System.err.println("input non valide");
                sc.nextLine();
            }
        }

    }

    public static void main(String[] args) {
        List start = new ArrayList();
        start.add(7);
        start.add(100);
        start.add(25);
        start.add(3);
        start.add(50);
        start.add(5);
        int target = random.nextInt(101,999);
        AlgoNombres.findAllSolutions(start,target);
//        List<Integer> output = new ArrayList<>();
//        System.out.println(test);
//            for (int i =0;i < test.size(); i++){//pour chaque couple de la liste on effectue une des 4 opérations
//               IntPair nbr = test.get(i);
//               int sum = AlgoNombres.Sum(nbr.x , nbr.y);
//               int sub = AlgoNombres.Substract(nbr.x,nbr.y);
//               int div = AlgoNombres.Division(nbr.x,nbr.y);
//               int multi = AlgoNombres.Multi(nbr.x,nbr.y);
//
//               output.add(sum);output.add(sub);output.add(div);output.add(multi);//on range les résultat dans dans une nouvelle liste
//                System.out.println("somme de " + nbr + " = "+ sum +
//                        " différence de " + nbr + " = " +sub +
//                        " division de " + nbr +" = " + div + " produit de" + nbr +  " = " + multi);
//            }
//                for (int j =0;j < start.size(); j++){//on effectue les opérations pour tous les nombres de la nouvelles listes avec les nombres de la première liste
//                    for(int k = 0; k < output.size(); k++){
//                        int startnbr = (int) start.get(j);
//                        int outputnbr = output.get(k);
//                        int sum1 = AlgoNombres.Sum(startnbr,outputnbr);
//                        int sub1 = AlgoNombres.Substract(startnbr,outputnbr);
//                        int div1 = AlgoNombres.Division(startnbr,outputnbr);
//                        int multi1 = AlgoNombres.Multi(startnbr,outputnbr);
//
//                        System.out.println( sum1 +" "+ sub1 +" "+ div1 +" "+ multi1);
//                }
//            }




    }
}
