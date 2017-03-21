package edu.unicen.nbody;

import java.awt.Color;

/**
 * Based on the tutorial: http://physics.princeton.edu/~fpretori/Nbody/index.htm
 *
 */
public class Body {
    private static final double G = 6.673e-11;   // gravitational constant

    private double rx, ry;       // holds the cartesian positions
    private double vx, vy;       // velocity components 
    private double fx, fy;       // force components
    private double mass;         // mass
    private Color color;         // color (for fun)

    // create and initialize a new Body
    public Body(double rx, double ry, double vx, double vy, double mass, Color color) {
        this.rx    = rx;
        this.ry    = ry;
        this.vx    = vx;
        this.vy    = vy;
        this.mass  = mass;
        this.color = color;
    }

    // update the velocity and position using a timestep dt
    public void update(double dt) {
        vx += dt * fx / mass;
        vy += dt * fy / mass;
        rx += dt * vx;
        ry += dt * vy;
    }

    // returns the distance between two bodies
    public double distanceTo(Body b) {
        double dx = rx - b.rx;
        double dy = ry - b.ry;
        return Math.sqrt(dx*dx + dy*dy);
    }

    // set the force to 0 for the next iteration
    public void resetForce() {
        fx = 0.0;
        fy = 0.0;
    }

    // compute the net force acting between the body a and b, and
    // add to the net force acting on a
    public void addForce(Body b) {
        double dx = b.rx - this.rx;
        double dy = b.ry - this.ry;
        double dist = Math.sqrt(dx*dx + dy*dy);
        if (dist == 0.0d)
            return;
        double F = (G * this.mass * b.mass) / (dist*dist);
        this.fx += F * dx / dist;
        this.fy += F * dy / dist;
    }

    // convert to string representation formatted nicely
    @Override
    public String toString() {
        return "" + rx + ", "+ ry+ ", "+  vx+ ", "+ vy+ ", "+ mass;
    }

    public double getRx() {
        return rx;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public double getRy() {
        return ry;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getFx() {
        return fx;
    }

    public void setFx(double fx) {
        this.fx = fx;
    }

    public double getFy() {
        return fy;
    }

    public void setFy(double fy) {
        this.fy = fy;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
   
}