package cdu.manage;

public class Turtle implements Runnable{

    private double total = 1;
    private final double SPEED = 0.1;
    
    public void run() {
        while(total > 0) {
            try {
                total -= SPEED;
                System.out.println("***乌龟前进0.1米***");
                if(total == 0) {
                    System.out.println("乌龟赢得了比赛");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
