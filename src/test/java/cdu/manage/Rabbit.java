package cdu.manage;

import java.util.concurrent.Callable;

public class Rabbit implements Callable<Integer>{
    
    private double total = 1;
    private final double SPEED = 0.5;
    private double cache;

    public Integer call() throws Exception {
        while(total > 0) {
            try {
                if(cache == 2) {
                    cache = 0;
                    System.out.println("===兔子休息十秒钟===");
                    Thread.sleep(1000 * 10);
                }
                total -= SPEED;
                cache += SPEED;
                System.out.println("---兔子前进0.5米---");
                if(total == 0) {
                    System.out.println("兔子赢得了比赛");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

}
