package edu.isistan.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class RefletionClassAnnotation {

    public static void main(String[] args) {
        String className = "edu.isistan.reflection.example.Class1";
        try {
            Class<?> c = Class.forName(className);
            System.out.println("Class: " + c);
            System.out.println("\tDeclared methods: ");
            for(Method m: c.getDeclaredMethods()) {
                String params = Arrays.toString(m.getParameterTypes());
                params = params.substring(1, params.length() - 1);
                System.out.println("\t\t" + Modifier.toString(m.getModifiers()) +
                        " " + m.getReturnType() +
                        " " + m.getName() +
                        "(" + params + ")" + 
                        " isDeprecated? " + (m.getAnnotation(Deprecated.class) != null));
            }
        } catch (ClassNotFoundException e) {
            // What to do if the class is not present
            e.printStackTrace();
        }
        
    }

}
