package edu.isistan.producerconsumer;

public class Consumer implements Runnable {

    private int times;
    private int consumerTime;
    private Buffer<Integer> fixedBuffer;
    
    public Consumer(int times, int consumerTime, Buffer<Integer> fixedBuffer) {
        this.times = times;
        this.consumerTime = consumerTime;
        this.fixedBuffer = fixedBuffer;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for(int i = 0; i < this.times; i++) {
            Integer val = fixedBuffer.next();
            System.out.println(threadName + "- read: "+ val);
            synchronized (this) {
                try {
                    this.wait(this.consumerTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        System.out.println(threadName + "- Leaving consumer...");
    }

}
