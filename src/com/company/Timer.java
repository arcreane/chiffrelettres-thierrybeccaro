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

    /**
     * Player has a determined time to answer.
     */
    public static String getPlayerAnswer(int time) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer;
        System.out.println("Entrez votre réponse");
        while(time != 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            time--;
        }
        answer = br.readLine();
        System.out.printf("Le temps imparti est écoulé. Votre réponse est : %s", answer);
        br.close();
        return answer;
    }

}


