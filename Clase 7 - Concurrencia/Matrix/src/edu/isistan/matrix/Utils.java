package edu.isistan.matrix;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

import edu.isistan.matrix.mult.IMultiplication;
import edu.isistan.matrix.mult.SimpleMultiplication;
/** 
 *  @author Dr. Juan Manuel Rodriguez
*/
public class Utils {

	/**
	 * Inicializador de detección de excepciones no manejadas
	 * */
	static {
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread arg0, Throwable arg1) {
				System.err.println("Error en el thread: "+arg0.getName()+" ID: "+arg0.getId());
				arg1.printStackTrace();
				System.exit(1);
			}
		});
	}
	
	private static DeadlockDetectionThread DDT;
	
	/**
	 * Compara dos matrices
	 * @param a
	 * @param b
	 * @param Diferencia aceptable 1e10
	 * @return
	 */
	public static boolean equalMatrices(double[][] a,double[][] b, double epsilon){
		if(a.length == 0) {
		    if (b.length == 0) 
		        return true;
		    return false;
		}
	    int columns = a[0].length;
		if (a.length != b.length)
		    return false;
		for(int i = 0; i < a.length; i++) {
            double[] ar = a[i];
            double[] br = b[i];
		    if ((columns != ar.length) || (columns != br.length))
		        return false;
		    for(int j = 0; j< ar.length; j++)
		        if (Math.abs(ar[j]-br[i]) > epsilon)
		            return false;
		}
		return true;
	}
	/**
	 * Mide el tiempo de ejecución requerido para multiplicar dos matrices.
	 * @param a
	 * @param b
	 * @param m
	 * @return
	 */
	public static long measureTime(double[][] a,double[][] b, IMultiplication m){
		long start = System.currentTimeMillis();
		m.multiply(a, b);
		return System.currentTimeMillis()-start;
	}
	/**
	 * Genera una matriz cuadrada densa inicializada al azar
	 * @param size
	 * @return
	 */
	public static double[][] generateDenseSquareMatrix(int size){
	    double[][] res = new double[size][size];
		for (int i=0;i<size;i++)
			for (int j=0;j<size;j++)
				res[i][j] = Math.random();
		return res;
	}

	/**
	 * Verifica un algoritmo de multiplicaciÃ³n contra
	 * el algoritmo simple. Verifica los respultados tests veces.
	 * @param a
	 * @param b
	 * @param m
	 * @param tests
	 * @return
	 */
	public static boolean verifyMultiplication(double[][] a,double[][] b, IMultiplication m, int tests){
	    double[][] resEsp = (new SimpleMultiplication()).multiply(a,b);
		for (int i = 0;i<tests;i++) {
		    double[][] res = m.multiply(a, b);
			if (!equalMatrices(resEsp,res, 1e10))
				return false;
		}
		return true;
	}
	/**
	 * Activa la detección de deadlocks. Verifica el estado del 
	 * sistema un vez por segundo.
	 * Este método solo detecta deadlocks una vez que ocurren y
	 * no detecta potenciales deadlocks.  
	 * 
	 */
	public synchronized static void activateDeadlockDetection(){
		if(DDT!=null)
			return;
		DDT = new DeadlockDetectionThread();
		DDT.start();
	}
	/**
	 * Desactiva la detección de deadlock, si está se encuentra activada.
	 */
	public synchronized static void deactivateDeadlockDetection(){
		if(DDT!=null) { 
			DDT.setActive(false);
			DDT = null;
		}
	}
	
	private static class DeadlockDetectionThread extends Thread {
		private boolean active = true;

		public DeadlockDetectionThread() {
			super("DeadlockDetection");
			this.setDaemon(true);
		}

		@Override
		public void run() {
			ThreadMXBean bean = ManagementFactory.getThreadMXBean();
			Object wait =  new Object();
			while(this.isActive()){
				long[] threadIds = bean.findDeadlockedThreads(); 
				if (threadIds != null) {
					ThreadInfo[] infos = bean.getThreadInfo(threadIds);
					System.err.println("Deadlock detectado!!!");
					for (ThreadInfo info : infos) {
						System.err.println(info);
					}
					System.exit(1);
				}
				synchronized (wait) {
					try {
						wait.wait(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public synchronized boolean isActive() {
			return active;
		}

		public synchronized void setActive(boolean active) {
			this.active = active;
		}
		
	}
}
