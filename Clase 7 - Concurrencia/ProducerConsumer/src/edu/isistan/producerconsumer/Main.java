package edu.isistan.producerconsumer;

public class Main {

    public static void main(String[] args) {
        Buffer<Integer> fixedBuffer = new Buffer<>(10);
        int times = 100;
        int consumerTime = 100;
        int producerTime = 50;
        if(Math.random() >= .5) {
            System.out.println("The consumer is faster than the producer");
            consumerTime = 50;
            producerTime = 100;
        } else {
            System.out.println("The producer is faster than the consumer");
        }
        Thread prod = new Thread(new Producer(times, producerTime, fixedBuffer), "Producer");
        Thread cons = new Thread(new Consumer(times, consumerTime, fixedBuffer), "Consumer");
        prod.start();
        cons.start();
        System.out.println("Leaving main thread...");
    }

}
