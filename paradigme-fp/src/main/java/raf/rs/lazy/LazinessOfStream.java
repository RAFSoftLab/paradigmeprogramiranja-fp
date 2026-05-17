package raf.rs.lazy;

import java.util.List;
import java.util.stream.Stream;

public class LazinessOfStream {

    private static int length(final String name) {
        System.out.println("getting length for " + name);
        return name.length();
    }
    private static String toUpper(final String name ) {
        System.out.println("converting to uppercase: " + name);
        return name.toUpperCase();
    }

    public static void main(final String[] args) {
        List<String> names = List.of("Brad", "Kate", "Kim", "Jack", "Joe",
                "Mike", "Susan", "George", "Robert", "Julia", "Parker", "Benson");
        final String firstNameWith3Letters =
                names.stream()
                        .filter(name -> length(name) == 3)
                        .map(name -> toUpper(name))
                        .findFirst()
                        .orElse("");
        System.out.println(firstNameWith3Letters);
    }

    public static void testLazinessOfStream(List<String> names){
        Stream<String> namesWith3Letters =
                names.stream()
                        .filter(name -> length(name) == 3)
                        .map(name -> toUpper(name));
        System.out.println("Stream created, filtered, mapped...");
        System.out.println("ready to call findFirst...");
        final String firstNameWith3Letters =
                namesWith3Letters.findFirst()
                        .orElse("");
        System.out.println(firstNameWith3Letters);
    }
}
