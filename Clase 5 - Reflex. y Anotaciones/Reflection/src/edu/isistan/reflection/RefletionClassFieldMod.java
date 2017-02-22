package edu.isistan.reflection;

import java.lang.reflect.Field;


public class RefletionClassFieldMod {

    public static void main(String[] args) {
        //Data to add to an arraylist
        Object[] elem = new Object[10];
        elem[0] = "Hello world!! (through the looking glass)";
        elem[1] = "I am filling the ArrayList";
        elem[2] = "using field reflection!!!";
        int size = 3;
        //Creating and adding the data by reflection
        String className = "java.util.ArrayList";
        try {
            //Get the class definition
            Class<?> c = Class.forName(className);
            //Creating a new Instance 
            //consider c.getConstructor(Class<?>...) if there is another constructor
            Object o = c.newInstance(); 
            //Getting the relevant fields
            Field felementData = c.getDeclaredField("elementData");
            Field fsize = c.getDeclaredField("size");
            //Grants access to the fields
            felementData.setAccessible(true);
            fsize.setAccessible(true);
            //Modifying the fields
            felementData.set(o, elem);
            fsize.setInt(o, size);
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
            // calling a private method, constructor or modifying
            // a protected/private field
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // What to do if the field does not exists
            e.printStackTrace();
        } catch (SecurityException e) {
            // What to do if we have no permission to
            // access fields by reflection
            e.printStackTrace();
        } 
        
    }

}
