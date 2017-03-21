package edu.isistan.producerconsumer;

import java.util.LinkedList;
import java.util.List;

public class Buffer <T> {

    private List<T> elements;
    private int maxSize;
    
    public Buffer(int i) {
        this.elements = new LinkedList<>();
        this.maxSize = i;
    }

    public synchronized T next() {
        String threadName = Thread.currentThread().getName();
        while (this.elements.isEmpty()) {
            System.out.println(threadName + "- waiting because the buffer is EMPTY");
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        T el = this.elements.remove(0);
        this.notify();
        return el;
    }

    public synchronized void put(T i) {
        String threadName = Thread.currentThread().getName();
        while (this.elements.size() == this.maxSize) {
            System.out.println(threadName + "- waiting because the buffer is FULL");
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        this.elements.add(i);
        this.notify();
    }

}
