package raf.rs.curring;

import raf.rs.funcinterfejsi.Op;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Izracunavanje {

    public static void main(String[] args) {
        System.out.println(aritmetikaFunc(Op.DELJENJE).apply(5.0).apply(8.0));
    }

    public static Function<Double, Function<Double,Double>> aritmetikaFunc(Op op){
        if(op==Op.SABIRANJE)
            return x->y->x+y;
        if(op==Op.ODUZIMANJE)
            return x->y->x-y;
        if(op==Op.MNOZENJE)
            return x->y->x*y;
        if(op==Op.DELJENJE)
            return x->y->x/y;
        else return null;
    }


    public static BiFunction<Double,Double,Double> aritmetikaBiFunc(Op op){
        if(op==Op.SABIRANJE)
            return (x,y)->x+y;
        if(op==Op.ODUZIMANJE)
            return (x,y)->x-y;
        if(op==Op.MNOZENJE)
            return (x,y)->x*y;
        if(op==Op.DELJENJE)
            return (x,y)->x/y;
        else return null;
    }
}
