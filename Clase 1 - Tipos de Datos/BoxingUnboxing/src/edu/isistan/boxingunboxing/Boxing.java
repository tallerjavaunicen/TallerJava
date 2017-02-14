package edu.isistan.boxingunboxing;

/**
 * This class provides a test to assess primitive data
 * vs. boxing data in both memory usage and time. 
 * @author Juan Manuel Rodriguez
 *
 */
public class Boxing {

    public static void main(String[] args) {
        testPrimitive();
        testBoxing();
        
    }

    /**
     * Test double primitive time and memory. Creates a 65_536_000
     * array of doubles. Since a double takes 8 bytes, this
     * array need 500 Mb. of RAM. <br>
     * Then the array is fill with numbers from 0 to 65_535_999.
     */
    private static void testPrimitive() {
        // Fuerza un garbage Collection para liberar memoria
        System.gc(); 
        long primitiveTimeTest = System.currentTimeMillis();
        long memInitPrimitive = Utils.getUsedMemory();
        /* Crea un arreglo de doubles que ocupa 500mb.
         * double -> 64 bits -> 8 bytes
         * 500mb. / 8bytes = 65536000
         */
        double[] data = new double[65_536_000];
        long memCreatePrimitive = Utils.getUsedMemory();
        //Relleno el arreglo con datos
        for(int i = 0; i < data.length; i++) {
            data[i] = i;
        }
        long memAfterPrimitive = Utils.getUsedMemory();
        primitiveTimeTest = System.currentTimeMillis() - primitiveTimeTest;

        System.out.println("Test Tipo Primitivo");
        System.out.println("\tTiempo total: " + primitiveTimeTest + " ms.");
        System.out.println("\tMemoria Usada al inicio: "+Utils.formatMem(memInitPrimitive));
        System.out.println("\tMemoria Usada despues de crear el arreglo: "+Utils.formatMem(memCreatePrimitive));
        System.out.println("\tMemoria Usada despues de cargar el arreglo: "+Utils.formatMem(memAfterPrimitive));
    }
    /**
     * Test Double boxing time and memory. Creates a 65_536_000
     * array of Double. <br>
     * Then the array is fill with numbers from 0 to 65_535_999. 
     * This requires a boxing of the numbers.
     */
    private static void testBoxing() {
        //Test Boxing
        System.gc();
        long boxingTimeTest = System.currentTimeMillis();
        long memInitBoxing = Utils.getUsedMemory();
        /* Crea un arreglo de Object que ocupa ???
         */
        Double[] dataBox = new Double[65_536_000];
        long memCreateBoxing = Utils.getUsedMemory();
        //Relleno el arreglo con datos
        for(int i = 0; i < dataBox.length; i++) {
            dataBox[i] = (double) i;
        }
        long memAfterBox = Utils.getUsedMemory();
        boxingTimeTest = System.currentTimeMillis() - boxingTimeTest;

        System.out.println("Test Boxing");
        System.out.println("\tTiempo total: " + boxingTimeTest + " ms.");
        System.out.println("\tMemoria Usada al inicio: "+Utils.formatMem(memInitBoxing));
        System.out.println("\tMemoria Usada despues de crear el arreglo: "+Utils.formatMem(memCreateBoxing));
        System.out.println("\tMemoria Usada despues de cargar el arreglo: "+Utils.formatMem(memAfterBox));
    }

}
