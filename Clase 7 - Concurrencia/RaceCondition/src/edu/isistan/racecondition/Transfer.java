package edu.isistan.racecondition;

public class Transfer implements Runnable {

    private static final int TIMES = 100_000_000;
    
    private Account origin;
    private Account target; 
    private int amount;
    
    public Transfer(Account origin, Account target, int amount) {
        super();
        this.origin = origin;
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void run() {
        for(int i = 1; i <= TIMES; i++) {
            this.origin.transfer(amount, target);
            if (i%(TIMES/10) == 0)
                System.out.println(this.origin);
        }
    }

}
