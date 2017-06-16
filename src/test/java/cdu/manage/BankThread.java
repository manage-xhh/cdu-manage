package cdu.manage;

public class BankThread implements Runnable{
    
    private int account_amount = 1000;
    private int check_out = 0;
    public BankThread(int check_out) {
        this.check_out = check_out;
    }
    
    public synchronized void run() {
        this.account_amount = this.account_amount - this.check_out;
        System.out.println(Thread.currentThread().getName() + "取出" + this.check_out);
        System.out.println("还剩余" + this.account_amount);     
    }

}
