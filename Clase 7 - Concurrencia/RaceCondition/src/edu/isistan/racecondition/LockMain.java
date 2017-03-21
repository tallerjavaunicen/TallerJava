package edu.isistan.racecondition;

import edu.isistan.racecondition.account.LockAccount;

public class LockMain {

    public static void main(String[] args) {
        Account a1 = new LockAccount(1, 1000);
        Account a2 = new LockAccount(2, 1000);
        Thread t1 = new Thread(new Transfer(a1, a2, 100));
        Thread t2 = new Thread(new Transfer(a2, a1, 100));
        long time = System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Finishing");
        System.out.println(a1);
        System.out.println(a2);
        System.out.println("The program run for "+time+" ms.");
    }

}
