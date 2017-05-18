package unicen.tallerjava;
import java.util.function.BiPredicate;
import java.util.function.IntBinaryOperator;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;

/**
 * Created by acorbellini on 22/02/17.
 */
public class Lambda {
    public static void main(String[] args) {
    	System.out.println("1".compareTo("2"));
    	System.out.println(Integer.valueOf(155).compareTo(Integer.valueOf(23)));
        BiPredicate<Integer, Integer> gt = (a, b) -> a > b;
        System.out.println(gt.test(2,1));
    }
}
