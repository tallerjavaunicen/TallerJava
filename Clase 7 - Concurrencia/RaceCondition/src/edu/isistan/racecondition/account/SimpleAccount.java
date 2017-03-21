package edu.isistan.racecondition.account;

import edu.isistan.racecondition.Account;

/**
 * Simple account implementation
 * @author Juan Manuel Rodriguez
 *
 */
public class SimpleAccount implements Account {
    private int id;
    private int balance;

    public SimpleAccount(int id, int balance) {
        super();
        this.id = id;
        this.balance = balance;
    }

    @Override
    public void withdraw(int amount) {
        this.balance -= amount;
    }

    @Override
    public void deposit(int amount) {
        this.balance += amount;
    }

    @Override
    public void transfer(int amount, Account target) {
        this.withdraw(amount);
        target.deposit(amount);
    }

    @Override
    public int getBalance() {
        return this.balance;
    }

    @Override
    public String toString() {
        return "SimpleAccount [id=" + id + ", balance=" + balance + "]";
    }

}
