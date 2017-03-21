package edu.isistan.racecondition.account;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Lock account implementation
 * @author Juan Manuel Rodriguez
 *
 */
public class LockAccount extends SimpleAccount{

    private Lock lock = new ReentrantLock();
    
    
    public LockAccount(int id, int balance) {
        super(id, balance);
    }


    @Override
    public void withdraw(int amount) {
        this.lock.lock();
        super.withdraw(amount);
        this.lock.unlock();
    }


    @Override
    public void deposit(int amount) {
        this.lock.lock();
        super.deposit(amount);
        this.lock.unlock();
    }


    @Override
    public int getBalance() {
        this.lock.lock();
        int balance = super.getBalance();
        this.lock.unlock();
        return balance;
    }

    @Override
    public String toString() {
        this.lock.lock();
        String res = super.toString();
        this.lock.unlock();
        return res;
    }

}
