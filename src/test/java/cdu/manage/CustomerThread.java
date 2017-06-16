package cdu.manage;

import java.util.concurrent.BlockingQueue;

public class CustomerThread implements Runnable{
    private BlockingQueue<String> queue;
    public CustomerThread(BlockingQueue<String> queue) {
        this.queue = queue;
    }
    
    public void run() {
        try{
            System.out.println(Thread.currentThread().getName() + "取出" + queue.take());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
