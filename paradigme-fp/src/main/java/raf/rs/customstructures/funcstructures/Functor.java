package raf.rs.customstructures.funcstructures;

import raf.rs.customstructures.RekList;

import java.util.function.Function;

public interface Functor<T>{

    <R> Functor<R> map(Function<T, R> function);
}
