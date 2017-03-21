package edu.isistan.racecondition;

public interface Account {

    public void withdraw(int amount);
    
    public void deposit(int amount);
    
    public void transfer(int amount, Account target);
    
    public int getBalance();
}
