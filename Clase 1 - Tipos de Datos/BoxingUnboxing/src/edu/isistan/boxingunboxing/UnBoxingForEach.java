package edu.isistan.boxingunboxing;

/**
 * Similar to {@link UnBoxing}, but the arrays are iterated with the for-each idiom.
 * @author Juan Manuel Rodriguez
 *
 */
public class UnBoxingForEach {

    public static void main(String[] args) {
        testPrimitive();
        testBoxing();
        
    }

    /**
     * Similar to {@link UnBoxing#testPrimitive}, but the array is iterated with the for-each idiom.
     */
    private static void testPrimitive() {
        // Fuerza un garbage Collection para liberar memoria
        System.gc(); 
        double[] data = new double[65_536_000];
        //Relleno el arreglo con datos
        for(int i = 0; i < data.length; i++) {
            data[i] = i;
        }
        long primitiveTimeTest = System.currentTimeMillis();
        double sum = 0;
        for (double d: data) {
            sum += d;
        }
        primitiveTimeTest = System.currentTimeMillis() - primitiveTimeTest;
        
        System.out.println("Test Tipo Primitivo");
        System.out.println("\tTiempo total: " + primitiveTimeTest + " ms.");
        System.out.println("\tSuma: "+ sum);
    }

    /**
     * Similar to {@link UnBoxing#testBoxing}, but the array is iterated with the for-each idiom.
     */
    private static void testBoxing() {
        //Test Boxing
        System.gc();
        Double[] dataBox = new Double[65_536_000];
        //Relleno el arreglo con datos
        for(int i = 0; i < dataBox.length; i++) {
            dataBox[i] = (double) i;
        }
        long boxingTimeTest = System.currentTimeMillis();
        double sum = 0;
        for (Double d: dataBox) {
            sum += d;
        }
        boxingTimeTest = System.currentTimeMillis() - boxingTimeTest;
        
        System.out.println("Test Boxing");
        System.out.println("\tTiempo total: " + boxingTimeTest + " ms.");
        System.out.println("\tSuma: "+ sum);
    }

}
