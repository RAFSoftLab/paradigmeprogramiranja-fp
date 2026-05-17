package raf.rs.funcinterfejsi;

import java.util.Optional;
import java.util.function.Function;

public class AndThenDemo {

    public static void main(String[] args) {
        Function<Integer, Integer> multiply = (value) -> value * 2;
        Function<Integer, Integer> add      = (value) -> value + 3;

        Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add); // prvo množenje pa sabiranje
        Function<Integer, Integer> addComposeMultiply = multiply.compose(add);  // prvo sabiranje pa množenje


        Integer result1 = multiplyThenAdd.apply(3);
        Integer result2 = addComposeMultiply.apply(3);

        System.out.println(result1);
        System.out.println(result2);


        Optional<Integer> brOp = Optional.of(5);

        boolean drugaZakonitost = brOp.map(multiplyThenAdd).equals(brOp.map(multiply).map(add));
        System.out.println(drugaZakonitost);


    }

}
