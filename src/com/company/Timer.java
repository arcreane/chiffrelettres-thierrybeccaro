package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Timer {
    /**
     * Return 0 when Timer is finished to initiate other actions.
     * int time : set desired time span
     */

    public static int DisplayTimer(String label, int time){
        System.out.println("Debut");
        for (int i = time; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s %ds   \r",label , i);// Reprint on the same line
        }
        System.out.println("Fin");
        return 0;
    }


}


