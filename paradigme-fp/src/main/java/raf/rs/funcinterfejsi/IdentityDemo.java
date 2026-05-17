package raf.rs.funcinterfejsi;

import java.util.Optional;
import java.util.function.Function;

public class IdentityDemo {

    public static void main(String[] args) {
        Optional<Integer> brOp = Optional.of(5);
        boolean prvaZakonitost = brOp.map(Function.identity()).equals(Function.identity().apply(brOp));
        System.out.println(prvaZakonitost);

    }

}
