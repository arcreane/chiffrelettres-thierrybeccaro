package com.company;

import java.util.ArrayList;

public class CalculateAnswerChiffres {
    /**
     * Checks if String entry is an int
     * Return a Boolean
     */
    private static boolean isInt(String string){
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Calculate the String returned by splitString function
     * Return an int
     */
    protected static int calculation(String Cal){
        if (isInt(Cal)){
            return Integer.parseInt(Cal);
        }
        //Extract each thing
        //Determine operand and split string with it

        String left = removeParenthesis(splitString(Cal,0));
        String op = splitString(Cal, 1);
        String right = removeParenthesis(splitString(Cal, 2));

        switch(op){
            case "+" -> {
                return calculation(left) + calculation(right);
            }
            case "-" -> {
                return calculation(left) - calculation(right);
            }
            case "*" -> {
                return calculation(left) * calculation(right);
            }
            case "/" -> {
                return calculation(left) / calculation(right);
            }
            default -> {
                return 0;
            }

        }
    }

    /**
     * Cut the string at the least prior calculation
     * Return the operand and the two sides of the calculation in an ArrayList
     */
    private static String splitString(String string, int index){
        ArrayList<String> list = new ArrayList<>();
        int cutPoint = 0;
        int count = 0;
        int startCut = 0;
        int endCut = string.length();
        boolean parenthesis = false;
        boolean cut = false;

        //First check if there are parenthesis
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c == '(') {
                parenthesis = true;
                break;
            }
        }

        //Second loop to cut at the least prior calculation
        boolean end = false;
        while(!cut) {
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                if (c == '(') {
                    count++;
                }
                if (c == ')') {
                    count--;
                }
                if (!parenthesis || count == 0) {
                    if (c == '+' || c == '-') {
                        cutPoint = i;
                        cut = true;
                        break;
                    } else if ((c == '*' || c == '/') && end) {
                        cutPoint = i;
                        cut = true;
                        break;
                    }
                }
            }
            end = true;
        }
        // Return the 3 chunks of string
        list.add(string.substring(startCut, cutPoint));
        list.add(string.substring(cutPoint, cutPoint+1));
        list.add(string.substring(cutPoint+1, endCut));
        //System.out.println(list);
        return list.get(index);
    }

    /**
     * Remove parenthesis only when there are two on each side
     */
    private static String removeParenthesis(String string){
        char fc = string.charAt(0);
        char lc = string.charAt(string.length()-1);
        if(fc == '('&& lc ==')'){
            //System.out.println(string.substring(1, string.length()-1));
            return string.substring(1, string.length()-1);
        }
        return string;
    }
}
