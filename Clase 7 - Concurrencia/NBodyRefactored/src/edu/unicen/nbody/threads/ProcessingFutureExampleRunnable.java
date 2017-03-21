package edu.unicen.nbody.threads;

import edu.unicen.nbody.Body;

/**
 * Based on the tutorial: http://physics.princeton.edu/~fpretori/Nbody/index.htm
 * Code refactored by Juan Manuel Rodriguez
 */
public class ProcessingFutureExampleRunnable implements Runnable {

    private int init;
    private int end;
    private Body[] bodies;

    public ProcessingFutureExampleRunnable(int init, int end, Body[] bodies) {
        super();
        this.init = init;
        this.end = end;
        this.bodies = bodies;
    }

    @Override
    public void run() {
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

    }

}
