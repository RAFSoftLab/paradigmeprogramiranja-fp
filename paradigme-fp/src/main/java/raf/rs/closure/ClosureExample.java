package raf.rs.closure;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ClosureExample {
        public static Function<Integer, Integer> createMultiplier(int factor) {
            // za funkciju koju vracamo factor je slobodna promenljiva
            return value -> value * factor; // closure
        }

        public static void main(String[] args) {
            Function<Integer, Integer> multiplyBy2 = createMultiplier(2);
            Function<Integer, Integer> multiplyBy3 = createMultiplier(3);

            System.out.println(multiplyBy2.apply(5)); // Output: 10
            System.out.println(multiplyBy3.apply(5)); // Output: 15
        }



    public static BiFunction<Integer, Integer,Integer> primer(int a, int b, int c) {
            return (x,y) -> a * x - y/b; // closure
    }
}



