package raf.rs.funcinterfejsi;

import java.util.function.Function;

public class Izracunavanje {

    public static Operacija aritmetika(Op op){
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

    public static void main(String[] args) {
        Operacija f = aritmetika(Op.SABIRANJE);
        System.out.println(f.izracunaj(3,4));
        Operacija p = aritmetika(Op.MNOZENJE);
        System.out.println(p.izracunaj(3,4));







    }



}
