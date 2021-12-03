package com.company;

import java.util.ArrayList;
import java.util.List;

import static com.company.AlgoChiffre.getPairs;

public class IntPair {
    int x;
    int y;

    public IntPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        String pair = ("pair = " + this.x + " : " + this.y);
        return pair;
    }

//    protected static List pairSum(List<IntPair> pairList) {
//        List<Integer> newVList = new ArrayList<>();
//        List<IntPair> newPList ;
//        for (IntPair pair : pairList) {
//            int sum = pair.x + pair.y;
//            newVList.add(sum);
//        }
//        newPList = getPairs(newVList);
//        return newVList;
//    }
//
//    protected static List pairSub(List<IntPair> pairList) {
//        List<Integer> newVList = new ArrayList<>();
//        List<IntPair> newPList ;
//        for (IntPair pair : pairList) {
//            if (pair.x > pair.y) {
//                int sub = pair.x - pair.y;
//                newVList.add(sub);
//            } else if (pair.y > pair.x) {
//                int sub = pair.y - pair.x;
//                newVList.add(sub);
//            }
//        }
//        newPList = getPairs(newVList);
//        return newVList;
//    }
//
//    protected static List pairMulti(List<IntPair> pairList) {
//        List<Integer> newVList = new ArrayList<>();
//        List<IntPair> newPList ;
//        for (IntPair pair : pairList) {
//            if (pair.x != 0 && pair.y != 0) {
//                int multi = pair.x * pair.y;
//                newVList.add(multi);
//            }
//        }
//        newPList = getPairs(newVList);
//        return newVList;
//    }
//
//
//    protected static  List pairDiv(List<IntPair> pairList) {
//        List<Integer> newVList = new ArrayList<>();
//        List<IntPair> newPList;
//        for (IntPair pair : pairList) {
//            if (pair.x != 0 && pair.y != 0) {
//                if (pair.x > pair.y) {
//                    int div = pair.x / pair.y;
//                    newVList.add(div);
//                } else if (pair.y > pair.x) {
//                    int div = pair.y / pair.x;
//                    newVList.add(div);
//                }
//            }
//        }
//        newPList = getPairs(newVList);
//        return newVList;
//    }
//
//    protected static void checkResult(List<Integer> List,int result){
//        for(int a : List){
//            if(a == result){
//
//            }
//        }
//    }
}
