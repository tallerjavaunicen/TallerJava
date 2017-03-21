package edu.isistan.racecondition.account;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Read/Write lock account implementation
 * @author Juan Manuel Rodriguez
 *
 */
public class ReadWriteLockAccount extends SimpleAccount{

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    
    
    public ReadWriteLockAccount(int id, int balance) {
        super(id, balance);
    }


    @Override
    public void withdraw(int amount) {
        this.lock.writeLock().lock();
        super.withdraw(amount);
        this.lock.writeLock().unlock();
    }


    @Override
    public void deposit(int amount) {
        this.lock.writeLock().lock();
        super.deposit(amount);
        this.lock.writeLock().unlock();
    }


    @Override
    public int getBalance() {
        this.lock.readLock().lock();
        int balance = super.getBalance();
        this.lock.readLock().unlock();
        return balance;
    }


    @Override
    public String toString() {
        this.lock.readLock().lock();
        String res = super.toString();
        this.lock.readLock().unlock();
        return res;
    }

}
