package raf.rs.izuzeci.dataorexception1;

import raf.rs.customstructures.funcstructures.Functor;

import java.util.function.Function;

public sealed interface DataOrException<T>  extends Functor<T> {
    public default boolean isDataPresent() { return true; }
    public default T getDataOrThrow() {
        throw new RuntimeException("No data");
    }
    public default Exception getExceptionOrThrow() {
        throw new RuntimeException("No Exception");
    }
    public static <T> DataOrException<T> of(T data) {
        return new Data<T>(data);
    }
    public static <T> DataOrException<T> of(Exception exception) {
        return new TheException<T>(exception);
    }

    @Override
    public <R> DataOrException<R> map(Function<T, R> function);

}

record Data<T>(T data) implements DataOrException<T> {
    public T getDataOrThrow() { return data; }

    @Override
    public <R> DataOrException<R> map(Function<T, R> function) {
        try{
            R rez = function.apply(data);
            return DataOrException.of(rez);
        }catch(Exception ex){
            return DataOrException.of(ex);
        }

    }
}

record TheException<T>(Exception exception) implements DataOrException<T> {
    public boolean isDataPresent() { return false; }
    public Exception getExceptionOrThrow() { return exception; }

    @Override
    public <R> DataOrException<R> map(Function<T, R> function) {
        return DataOrException.of(exception);
    }
}


