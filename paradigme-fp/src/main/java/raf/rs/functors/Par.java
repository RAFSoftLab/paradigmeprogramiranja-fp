package raf.rs.functors;

import java.util.function.Function;

public class Par<T> {
    final private T elem1, elem2;

    private Par(T elem1, T elem2) {
        this.elem1 = elem1;
        this.elem2 = elem2;
    }

    public static <T> Par<T> of(T elem1, T elem2){
        return new Par(elem1,elem2);
    }

    public <R> Par<R> map(Function<T,R> func){
        return Par.of(func.apply(elem1),func.apply(elem2));
    }

    @Override
    public String toString() {
        return "Par{" +
                "elem1=" + elem1 +
                ", elem2=" + elem2 +
                '}';
    }
}
