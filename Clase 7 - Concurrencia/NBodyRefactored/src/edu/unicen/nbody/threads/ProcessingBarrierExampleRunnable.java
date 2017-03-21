package edu.unicen.nbody.threads;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import edu.unicen.nbody.Body;

/**
 * Based on the tutorial: http://physics.princeton.edu/~fpretori/Nbody/index.htm
 * Code refactored by Juan Manuel Rodriguez
 */
public class ProcessingBarrierExampleRunnable implements Runnable {

    private CyclicBarrier barrier;
    private int init;
    private int end;
    private Body[] bodies;

    public ProcessingBarrierExampleRunnable(CyclicBarrier barrier, int init, int end, Body[] bodies) {
        super();
        this.barrier = barrier;
        this.init = init;
        this.end = end;
        this.bodies = bodies;
    }

    @Override
    public void run(){
        this.runInner();
        System.err.println("Finishing "+ Thread.currentThread().getName());
    }
    
    private void runInner() {
        while(true){
            try {
                //Wait for start calculation
                barrier.await();
            } catch (InterruptedException e) {
                return;
            } catch (BrokenBarrierException e) {
                return;
            }


            for (int j = init; j < end; j++) {
                bodies[j].resetForce();
                //Notice-2 loops-->N^2 complexity
                for (int k = 0; k < bodies.length; k++) {
                    if (j != k) bodies[j].addForce(bodies[k]);
                }
            }
            //Then, loop again and update the bodies using timestep dt
            for (int j = init; j < end; j++) {
                bodies[j].update(1e11);
            }

            try {
                //Wait for results
                barrier.await();
            } catch (InterruptedException e) {
                return;
            } catch (BrokenBarrierException e) {
                return;
            }
        }

    }
}
