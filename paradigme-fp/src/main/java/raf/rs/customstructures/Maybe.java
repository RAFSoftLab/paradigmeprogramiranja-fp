package raf.rs.customstructures;

import java.util.function.Function;

public class Maybe<T> {
    private final T value;
    private boolean isNothing;

    private Maybe(T value){
        this.value = value;
        isNothing = false;
    }

    private Maybe(){
        this.value = null;
        isNothing = true;
    }

    public static <T> Maybe<T> createNothing(){
        return new Maybe();
    }

    public static <T> Maybe<T> just(T value){
        return new Maybe<T>(value);
    }

    public boolean isNothing(){
        return isNothing;
    }

    public <R> Maybe<R> map(Function<T,R> func){
        if(this.isNothing)
            return createNothing();
        else return just(func.apply(value));
    }

}
