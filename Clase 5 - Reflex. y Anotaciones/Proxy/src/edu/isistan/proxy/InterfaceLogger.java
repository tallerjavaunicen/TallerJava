package edu.isistan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InterfaceLogger implements InvocationHandler {

    private Object instance;
    
    public InterfaceLogger(Object instance) {
        this.instance = instance;
    }

    /**
     * What to do when the method is invoked.
     */
    @Override
    public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
        StringBuilder log = new StringBuilder();
        String args = Arrays.toString(arg2);
        args = args.substring(1, args.length()-1);
        log.append("Calling: ").append(arg1.getName()).append('(')
            .append(args).append(')').append('\n');
        try {
            //Invoke by reflection!!
            Object ret = arg1.invoke(this.instance, arg2);
            log.append("\tReturning: ");
            log.append(ret);
            return ret;
        } catch (InvocationTargetException e) {
            log.append("\tException generated: " + e.getTargetException());
            throw e.getTargetException();
        } finally {
            System.out.println(log);
        }
    }


}
