package raf.rs.lazy;

import java.util.List;
import java.util.stream.IntStream;

public class InfiniteCollections {

    public static boolean isPrime(final int number) {
        return number > 1 &&
                IntStream.rangeClosed(2, (int) Math.sqrt(number))
                        .noneMatch(divisor -> number % divisor == 0);
    }

    /* kreiranje liste svih prostih brojeva počevši od zadatg broja
     ovaj kod proizvod StackOverﬂowError
    public static List<Integer> primes(final int number) {
        if(isPrime(number)) {
            return concat(number, primes(number + 1));
        }
        return primes(number + 1);
    }

     */

}
