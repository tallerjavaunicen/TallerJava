package edu.isistan.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RefletionClassInfo {

    public static void main(String[] args) {
        String className = "java.util.ArrayList";
        try {
            Class<?> c = Class.forName(className);
            System.out.println("Class: " + c);
            System.out.println("\tDeclared fields: ");
            for(Field f: c.getDeclaredFields()) {
                System.out.println("\t\t" + Modifier.toString(f.getModifiers()) +
                        " " + f.getType() +
                        " " + f.getName());
            }
            System.out.println("\tDeclared methods: ");
            for(Method m: c.getDeclaredMethods()) {
                String params = Arrays.toString(m.getParameterTypes());
                params = params.substring(1, params.length() - 1);
                System.out.println("\t\t" + Modifier.toString(m.getModifiers()) +
                        " " + m.getReturnType() +
                        " " + m.getName() +
                        "(" + params + ")");
            }
            System.out.println("Other accessible methods: ");
            Set<Method> otherMethods = new HashSet<>(Arrays.asList(c.getMethods()));
            otherMethods.removeAll(Arrays.asList(c.getDeclaredMethods()));
            for(Method m: otherMethods) {
                String params = Arrays.toString(m.getParameterTypes());
                params = params.substring(1, params.length() - 1);
                System.out.println("\t\t" + Modifier.toString(m.getModifiers()) +
                        " " + m.getReturnType() +
                        " " + m.getName() +
                        "(" + params + ")" + 
                        " Declared by: " + m.getDeclaringClass());
            }
        } catch (ClassNotFoundException e) {
            // What to do if the class is not present
            e.printStackTrace();
        }
        
    }

}
