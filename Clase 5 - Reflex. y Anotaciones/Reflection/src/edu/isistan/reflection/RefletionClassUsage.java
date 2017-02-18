package edu.isistan.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class RefletionClassUsage {

    public static void main(String[] args) {
        String className = "java.util.ArrayList";
        try {
            //Get the class definition
            Class<?> c = Class.forName(className);
            //Creating a new Instance 
            //consider c.getConstructor(Class<?>...) if there is another constructor
            Object o = c.newInstance(); 
            //Geting the method by name and input parameters
            Method m = c.getMethod("add", Object.class);
            //Invoking the method in the object
            m.invoke(o, "Hello world!! (through the looking glass)");
            m.invoke(o, "I am invoking the add method");
            m.invoke(o, "using refletion!!!");
            
            //Checking that our object is an arraylist
            System.out.println("Is an ArrayList? " + (o instanceof java.util.ArrayList<?>));
            //What has the arraylist
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            // What to do if the class is not present
            e.printStackTrace();
        } catch (InstantiationException e) {
            // What to do if there is no default constructor
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // What to do if there is an illegal access, like 
            // calling a private method o constructor
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            //What to do if there is not such method
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            //What to do if there invoked method
            //throws an exception
            e.printStackTrace();
        }
        
    }

}
