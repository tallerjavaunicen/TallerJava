package edu.isistan.reflection.example;

public class Class1 {

    private int intPrivate;
    protected int intProtected;
    
    public Class1(){
        System.out.println("Constructing class 1");
    }

    public Class1(int intPrivate, int intProtected) {
        super();
        this.intPrivate = intPrivate;
        this.intProtected = intProtected;
    }

    /**
     * Adds instance fields
     * @return
     */
    public int process(){
        System.out.println("Processing...");
        return intPrivate + intProtected;
    }
    
    /**
     * Adds instance fields
     * @return
     * @deprecated This method has been deprecated in favor of process
     */
    @Deprecated
    public int oldAdd(){
        System.out.println("Processing...");
        return intPrivate + intProtected;
    }
}
