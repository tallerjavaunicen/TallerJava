package edu.isistan.boxingunboxing;

/**
 * Provides methods for assesing memory usage
 * @author Juan Manuel Rodriguez
 *
 */
public class Utils {

    /**
     * Calculates current process memory usage.
     * @return memory usage
     */
    public static long getUsedMemory(){
        Runtime rt = Runtime.getRuntime();
        long freeMem = rt.totalMemory() - rt.freeMemory();    
        return freeMem;
    }

    /**
     * Format bytes to Gb., Mb. or Kb.
     * @param memory in bytes
     * @return formated string.
     */
    public static String formatMem(long memory) {
        double mem = memory;
        if (mem / (1024L * 1024L * 1024L) > 1) {
            return Double.toString(mem / (1024L * 1024L * 1024L)) + " Gb.";
        } else if (mem / 1024L * 1024L > 1) {
            return Double.toString(mem / (1024L * 1024L)) + " Mb.";
        }
        return Double.toString(mem / 1024L) + " Kb.";   
    }
}
