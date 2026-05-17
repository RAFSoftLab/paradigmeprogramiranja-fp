package raf.rs.lazy;

import java.util.function.Supplier;

public class Evaluation {

    public static boolean evaluate(final int value) {
        System.out.println("evaluating ..." + value);
        simulateTimeConsumingOp(value);
        return value > 3000;
    }

    private static void simulateTimeConsumingOp(int n){
        try {
            Thread.sleep(n);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eagerEvaluator(final boolean input1, final boolean input2) {
        System.out.println("eagerEvaluator called...");
        System.out.println("accept?: " + (input1 && input2));
    }

    public static void lazyEvaluator(final Supplier<Boolean> input1, final Supplier<Boolean> input2) {
        System.out.println("lazyEvaluator called...");
        System.out.println("accept?: " + (input1.get() && input2.get()));
    }

    public static void main(String[] args) {
       // eagerEvaluator(evaluate(2500), evaluate(6000));

        lazyEvaluator(() -> evaluate(2500), () -> evaluate(6000));
    }

}
