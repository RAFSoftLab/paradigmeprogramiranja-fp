package raf.rs.funcinterfejsi;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class BiFunctionPrimer {

    public static void main(String[] args) {
        BiFunction<String, Integer, String> podstring = (str,index)-> str.substring(index);
        //System.out.println(podstring.apply("mamababa",5));

        // kombanicija listi
        List<String> stringovi = List.of("a","b","c","d");
        List<Integer> brojevi = List.of(3,2,6,7);
        List<String> rez = new ArrayList<>();
        for(int i=0;i<stringovi.size();i++){
            rez.add(stringovi.get(i)+brojevi.get(i));
        }
        //System.out.println(rez);


        System.out.println(listKombinator(stringovi,brojevi,(x,y)->x+y));

        System.out.println(listKombinator(List.of(1,2,3,4),List.of(4,2,1,5),(x,y)->x-y));

        System.out.println(listKombinator(List.of(1,2,3,4),List.of(4,2,1,5),(x,y)->x>y));
    }


    public static <T,U,R> List<R> listKombinator(List<T> list1, List<U> list2, BiFunction<T, U, R> kombinator){
        List<R> rez = new ArrayList<>();
        for(int i=0;i<list1.size();i++){
            rez.add(kombinator.apply(list1.get(i), list2.get(i)));
        }
        return rez;
    }


}
