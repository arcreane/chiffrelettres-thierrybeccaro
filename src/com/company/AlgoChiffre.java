package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AlgoChiffre {

    protected static void getAnswer(List<Integer> list){
//        List pairs = new ArrayList();
//            for(int i = 0; i < list.size(); i++){
//                int index = list.get(i);
//                int pair = new IntPair()
//            }

        }
        protected static List getPairs(List<Integer> list){
            List<IntPair> pairs = new ArrayList();
            for(int i = 0; i < list.size()-1; i++){
                for(int j = i+1;j <= list.size()-1 ; j++){
//                    if(j == list.size()-1 && i > 0 ){
//                        j = 0;
//                    }
                    IntPair pair = new IntPair(list.get(i),list.get(j));
                    System.out.println(pair.x + " ; " +pair.y);
                    pairs.add(pair);
                }
        }

            return pairs;
    }
}

