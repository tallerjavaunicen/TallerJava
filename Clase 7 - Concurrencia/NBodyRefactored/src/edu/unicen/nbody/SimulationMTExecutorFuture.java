package edu.unicen.nbody;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;

//required to paint on screen
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.unicen.nbody.threads.ProcessingFutureExampleRunnable;


/**
 * Based on the tutorial: http://physics.princeton.edu/~fpretori/Nbody/index.htm
 * Code refactored by Juan Manuel Rodriguez
 */
public class SimulationMTExecutorFuture extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 7505333197481537020L;
    private int nBodies = 100;
    private Body bodies[];
    private JTextField editText;
    private JLabel label;
    private JButton stop;
    private JButton restart;
    private JCheckBox bechmark; 
    private volatile boolean shouldrun=false;
    private volatile Semaphore waitPaint;

    public static void main(String[] args) {
        (new SimulationMTExecutorFuture()).init();
    }

    //Init the graphical interface
    public void init()
    {
        startthebodies(nBodies);
        editText=new JTextField("100",5);
        restart=new JButton("Restart");
        stop=new JButton("Stop");
        stop.setEnabled(false);
        label=new JLabel("Number of bodies:");
        ButtonListener myButtonListener = new ButtonListener();
        stop.addActionListener(myButtonListener);
        restart.addActionListener(myButtonListener);
        bechmark = new JCheckBox("Bechmark");
        bechmark.setSelected(false);

        JFrame jFrame = new JFrame("NBody");
        jFrame.setBounds(100, 100, 1028, 1028);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setLayout(new BorderLayout());
        jFrame.add(this, BorderLayout.CENTER);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(label);
        panel.add(editText);
        panel.add(restart);
        panel.add(stop);
        panel.add(bechmark);
        jFrame.add(panel, BorderLayout.PAGE_END);;
        jFrame.setVisible(true);
    }


    //This method gets called when the applet is terminated. It stops the code
    public void stop()
    {
        shouldrun=false;
        if((waitPaint !=null) && (waitPaint.availablePermits()==0))
            waitPaint.release();
    }


    //Called by the applet initally. It can be executed again by calling repaint();
    public void paint(Graphics g)
    {
        int w = this.getWidth()/2;
        int h = this.getHeight()/2;
        g.translate(w, h); //Originally the origin is in the top right. Put it in its normal place
        g.clearRect(-w, -w, 2*getWidth(), 2*getHeight());
        //check if we stopped the applet, and if not, draw the particles where they are
        if (shouldrun) {
            for(int i=0; i<nBodies; i++) {
                g.setColor(bodies[i].getColor());
                g.fillOval((int) Math.round(bodies[i].getRx()*w/1e18),(int) Math.round(bodies[i].getRy()*h/1e18),8,8);
            }
            this.waitPaint.release();
        }
    }

    //the bodies are initialized in circular orbits around the central mass.
    //This is just some physics to do that
    public static double circlev(double rx, double ry) {
        double solarmass=1.98892e30;
        double r2=Math.sqrt(rx*rx+ry*ry);
        double numerator=(6.67e-11)*1e6*solarmass;
        return Math.sqrt(numerator/r2);
    }

    //Initialize N bodies with random positions and circular velocities
    public void startthebodies(int N) {
        double solarmass=1.98892e30;
        bodies = new Body[N];
        for (int i = 0; i < N; i++) {
            double px = 1e18*exp(-1.8)*(.5-Math.random());
            double py = 1e18*exp(-1.8)*(.5-Math.random());
            double magv = circlev(px,py);

            double absangle = Math.atan(Math.abs(py/px));
            double thetav= Math.PI/2-absangle;
            double vx   = -1*Math.signum(py)*Math.cos(thetav)*magv;
            double vy   = Math.signum(px)*Math.sin(thetav)*magv;
            //Orient a random 2D circular orbit

            if (Math.random() <=.5) {
                vx=-vx;
                vy=-vy;
            } 

            double mass = Math.random()*solarmass*10+1e20;
            //Color the masses in green gradients by mass
            int red     = (int) Math.floor(mass*254/(solarmass*10+1e20));
            int blue   = (int) Math.floor(mass*254/(solarmass*10+1e20));
            int green    = 255;
            Color color = new Color(red, green, blue);
            bodies[i]   = new Body(px, py, vx, vy, mass, color);
        }
        //Put the central mass in
        bodies[0]= new Body(0,0,0,0,1e6*solarmass,Color.red);//put a heavy body in the center

    }

    public static double exp(double lambda) {
        return -Math.log(1 - Math.random()) / lambda;
    }

    public class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent evt) 
        {
            // Get label of the button clicked in event passed in
            String arg = evt.getActionCommand();    
            if (arg.equals("Restart"))
            {
                shouldrun=true;
                nBodies = Integer.parseInt(editText.getText());

                startthebodies(nBodies);
                waitPaint = new Semaphore(1);
                Thread t = new Thread(() -> {
                    int numProc = Runtime.getRuntime().availableProcessors();
                    int range = nBodies / numProc;
                    int mod = nBodies % numProc != 0 ? 1 : 0;
                    ProcessingFutureExampleRunnable[] runnables = new ProcessingFutureExampleRunnable[numProc];
                    //Creates runnables
                    for(int i = 0; i < numProc; i++) {
                        final int init = (range + mod)*i;
                        final int end = Math.min((range + mod)*(i+1), nBodies);
                        runnables[i] = new ProcessingFutureExampleRunnable(init, end, bodies);
                    }
                    
                    Future<?>[] futures = new Future<?>[numProc];
                    ExecutorService executor = Executors.newFixedThreadPool(numProc);
                    repaint();
                    while(shouldrun){
                        
                        long time = System.currentTimeMillis();
                        for(int i = 0; i < numProc; i++) {
                            futures[i] = executor.submit(runnables[i]);
                        }
                        for(Future<?> f: futures) {
                            try {
                                f.get();//wait to finish
                            } catch (InterruptedException e) {
                                break;
                            } catch (ExecutionException e) {
                                break;
                            }
                        }
                        if (bechmark.isSelected()){
                            System.err.println("Frame time: " + (System.currentTimeMillis()-time) + " ms." );
                        }
                        repaint();
                    }
                    executor.shutdownNow();
                    System.err.println("Finishing Drawing thread");
                });
                t.start();
                restart.setEnabled(false);
                stop.setEnabled(true);
                }
                else if (arg.equals("Stop")) {
                    stop();
                    restart.setEnabled(true);
                    stop.setEnabled(false);
                }
            }
        }

    }