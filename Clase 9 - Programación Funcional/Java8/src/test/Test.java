package test;

import java.util.function.Supplier;

public class Test {
    String str;

    void run() {
        str = "hello ";
	    Supplier<String> s1 = str::toUpperCase;
	    Supplier<String> s2 = () -> str.toUpperCase();
	    str = "Hotel Echo Lima Lima Oscar ";
    
    	System.out.println(s1.get());
	    
    	System.out.println(s2.get());
	}
    
    public static void main(String[] args) {
    	Test t = new Test();
    	t.run();
	}
    
}

