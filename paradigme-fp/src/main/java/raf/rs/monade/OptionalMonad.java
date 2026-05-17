package raf.rs.monade;

import java.util.Optional;
import java.util.function.Function;

public class OptionalMonad {

    public static void main(String[] args) {
        Optional<Integer> intOpt = Optional.of(5);
        Optional<Boolean> rez = intOpt.flatMap(x->Optional.of(x%2==0));
        System.out.println(rez);

        Function<Integer,Optional<Integer>> func = x->Optional.of(x*10);
        System.out.println(intOpt.map(func));
        System.out.println(intOpt.flatMap(func));

        Optional<String> optString = Optional.of("test");
        optString = optString
               // .flatMap(OptionalMonad::emptyOptionalString)
                .flatMap(OptionalMonad::doubleOptionalString)
                .flatMap(OptionalMonad::doubleOptionalString);

        System.out.println(optString);


        optionalSab(Optional.of(23),Optional.of(10)).ifPresent(System.out::println);

        optionalSab(Optional.empty(),Optional.of(10)).ifPresent(System.out::println);

    }

    private static Optional<String> doubleOptionalString(String str) {
        return Optional.ofNullable(str+str);
    }

    private static Optional<String> emptyOptionalString(String str) {
        return Optional.empty();
    }

    private static String doubleString(String str) {
        return str+str;
    }

    private static Optional<Integer> optionalSab(Optional<Integer> sab1, Optional<Integer> sab2){
        return      sab1.flatMap(prvi->
                        sab2.flatMap(drugi ->
                               Optional.of(prvi+drugi)));
    }
}
