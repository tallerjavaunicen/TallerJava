package collections;

import java.util.Collection;
import java.util.Set;

public class OptimisingDisjoint {

	public static boolean optimisedDisjoint(Collection<?> c1, Collection<?> c2) {
        /*
         * We're going to iterate through c1 and test for inclusion in c2.
         * If c1 is a Set and c2 isn't, swap the collections.  Otherwise,
         * place the shorter collection in c1.  Hopefully this heuristic
         * will minimize the cost of the operation.
         */
        if ((c1 instanceof Set) && !(c2 instanceof Set) ||
            (c1.size() > c2.size())) {
            Collection<?> tmp = c1;
            c1 = c2;
            c2 = tmp;
        }

        for (Object e : c1)
            if (c2.contains(e))
                return false;
        return true;
    }
	
	public static boolean disjoint(Collection<?> c1, Collection<?> c2) {

		for (Object e : c1)
            if (c2.contains(e))
                return false;
        return true;
    }
	
	public static void main(String[] args) {
		//Hacer las pruebas registrando tiempos!
	}
	
}
