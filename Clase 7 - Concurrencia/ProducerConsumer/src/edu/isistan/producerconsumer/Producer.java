package edu.isistan.producerconsumer;

public class Producer implements Runnable {
    private int times;
    private int producerTime;
    private Buffer<Integer> fixedBuffer;
    
    public Producer(int times, int producerTime, Buffer<Integer>  fixedBuffer) {
        this.times = times;
        this.producerTime = producerTime;
        this.fixedBuffer = fixedBuffer;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for(int i = 0; i < this.times; i++) {
            Integer val = i;
            this.fixedBuffer.put(val);
            System.out.println(threadName + "- put: "+ val);
            synchronized (this) {
                try {
                    this.wait(this.producerTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        System.out.println(threadName + "- Leaving producer...");
    }

}
