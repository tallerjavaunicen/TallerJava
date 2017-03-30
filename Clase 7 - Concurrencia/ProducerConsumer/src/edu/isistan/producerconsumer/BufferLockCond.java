package edu.isistan.producerconsumer;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BufferLockCond<T> implements IBuffer<T>{

    private List<T> elements;
    private int maxSize;
    private Lock mutex;
    private Condition cEmpty;
    private Condition cFull;

    public BufferLockCond(int i) {
        this.elements = new LinkedList<>();
        this.maxSize = i;
        this.mutex = new ReentrantLock();
        this.cEmpty = this.mutex.newCondition();
        this.cFull = this.mutex.newCondition();
    }

    public T next() {
        this.mutex.lock();
        try {
            String threadName = Thread.currentThread().getName();
            while (this.elements.isEmpty()) {
                System.out.println(threadName + "- waiting because the buffer is EMPTY");
                try {
                    this.cEmpty.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            T el = this.elements.remove(0);
            cFull.signal();
            return el;
        } finally {
            this.mutex.unlock();
        }
    }

    public void put(T i) {
        this.mutex.lock();
        try{
            String threadName = Thread.currentThread().getName();
            while (this.elements.size() == this.maxSize) {
                System.out.println(threadName + "- waiting because the buffer is FULL");
                try {
                    this.cFull.await();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            this.elements.add(i);
            this.cEmpty.signal();
        } finally {
            this.mutex.unlock();
        }
    }

}
