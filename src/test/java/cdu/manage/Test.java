package cdu.manage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);
        Rabbit rabbit = new Rabbit();
        Turtle turtle = new Turtle();
        Future<?> rFuture = pool.submit(rabbit);
        Future<?> tFuture = pool.submit(turtle);
        while(true) {
            if(rFuture.isDone() || tFuture.isDone()) {
                System.out.println("game over!");
                pool.shutdownNow();
                break;
            }
        }
    }
}
