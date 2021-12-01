package com.company;

public class Timer {
    public static int DisplayTimer(int time){
        String label = "Il vous reste";
        System.out.println("Debut");
        for (int i = time; i >= 0; i--) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s %ds.\r",label , i);

        }
        System.out.println("Fin");
        return 0;
    }
}
