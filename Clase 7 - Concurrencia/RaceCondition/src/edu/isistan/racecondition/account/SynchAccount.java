package edu.isistan.racecondition.account;

/**
 * Synchronized account implementation
 * @author Juan Manuel Rodriguez
 *
 */
public class SynchAccount extends SimpleAccount{

    
    public SynchAccount(int id, int balance) {
        super(id, balance);
    }


    @Override
    public synchronized void withdraw(int amount) {
        super.withdraw(amount);
    }


    @Override
    public synchronized void deposit(int amount) {
        super.deposit(amount);
    }


    @Override
    public synchronized int getBalance() {
        return super.getBalance();
    }


    @Override
    public  synchronized String toString() {
        return super.toString();
    }

}
