package edu.isistan.proxy;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Proxy;

public class ProxyMain {

    public static void main(String[] args) {
        Object o = Proxy.newProxyInstance(Proxy.class.getClassLoader(), 
                new Class<?>[]{List.class}, 
                new InterfaceLogger(new ArrayList<>()));
        @SuppressWarnings("unchecked")
        List<String> ls = (List<String>) o;
        ls.add("Hola");
        ls.add("mundo");
        ls.add("!!!");
        ls.get(1);
        try {
            ls.get(3);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Exception catch: " + e);
        }
        ls.contains("mundo");
        ls.remove(0);
        ls.remove("mundo");
    }

}
