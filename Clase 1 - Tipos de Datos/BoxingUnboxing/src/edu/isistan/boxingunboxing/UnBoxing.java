package edu.isistan.boxingunboxing;

/**
 * Test unboxing speed
 * @author Juan Manuel Rodriguez
 *
 */
public class UnBoxing {

    public static void main(String[] args) {
        testPrimitive();
        testBoxing();
        
    }

    /**
     * Creates an double array similar to {@link Boxing#testPrimitive}.
     * Then it measures the time that takes to accumulate all it values
     * in a double. The array is iterated by index.
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
        for (int i = 0; i < data.length; i++) {
            sum += data[i];
        }
        primitiveTimeTest = System.currentTimeMillis() - primitiveTimeTest;
        
        System.out.println("Test Tipo Primitivo");
        System.out.println("\tTiempo total: " + primitiveTimeTest + " ms.");
        System.out.println("\tSuma: "+ sum);
    }
    /**
     * Creates an Double array similar to {@link Boxing#testPrimitive}.
     * Then it measures the time that takes to accumulate all it values
     * in a double (an unboxing is requires). The array is iterated by index.
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
        for (int i = 0; i < dataBox.length; i++) {
            sum += dataBox[i];
        }
        boxingTimeTest = System.currentTimeMillis() - boxingTimeTest;
        
        System.out.println("Test Boxing");
        System.out.println("\tTiempo total: " + boxingTimeTest + " ms.");
        System.out.println("\tSuma: "+ sum);
    }

}
