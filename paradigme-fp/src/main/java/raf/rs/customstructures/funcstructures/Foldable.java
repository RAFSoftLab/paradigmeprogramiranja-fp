package raf.rs.customstructures.funcstructures;

import java.util.function.BiFunction;

public interface Foldable<T>    {

    public <U> U foldL(U acc, BiFunction<U, T, U> function);

    public <U> U foldR(U acc, BiFunction<T, U, U> function);

}
