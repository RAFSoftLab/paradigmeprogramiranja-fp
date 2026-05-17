package raf.rs.fold;

import java.util.Arrays;
import java.util.List;

public class TestReduceRazTipovi {

    public static void main(String[] args) {

        List<String> imena = Arrays.asList("Marija", "Milica", "Marko", "Anamarija", "Sava");

        int ukupnaDuzina = imena.stream().reduce(
                0, // identity (U)
                (sum, str) -> sum + str.length(), // accumulator (BiFunction<U, T, U>)
                Integer::sum // combiner (BinaryOperator<U>)
        );

    }
}
