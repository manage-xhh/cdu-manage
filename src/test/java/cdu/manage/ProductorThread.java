package cdu.manage;

import java.util.concurrent.BlockingQueue;

public class ProductorThread implements Runnable{

    private BlockingQueue<String> queue;
    public ProductorThread(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            this.queue.put("门票" + Thread.currentThread().getName().substring(Thread.currentThread().getName().lastIndexOf("-")+1));
            System.out.println(Thread.currentThread().getName() + "生产门票" + Thread.currentThread().getName().substring(Thread.currentThread().getName().lastIndexOf("-")+1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
