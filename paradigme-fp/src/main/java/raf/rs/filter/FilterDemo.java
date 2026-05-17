package raf.rs.filter;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FilterDemo {

    public static void main(String[] args) {
        Optional<Integer> brOpt = Optional.of(90);
        brOpt.filter(x->x%10==0).ifPresent(System.out::println);
        brOpt.filter(x->x%7==0).ifPresent(System.out::println);



        List<Integer> brList = List.of(1,6,5,2,8,0,3,20);
        brList.stream().filter(x->x>5).forEach(System.out::println);

        Predicate<Integer> pozitivan = x->x>0;
        Predicate<Integer> paran = x->x%2==0;

        brList.stream().filter(pozitivan.and(paran)).forEach(System.out::println);
        brList.stream().filter(pozitivan).filter(Predicate.not(paran)).forEach(System.out::println);

        Function<Integer,Predicate<Integer>> veciOd = x-> y-> x>y;
        brList.stream().filter(veciOd.apply(7)).forEach(System.out::println);




    }

    private static void printNumWithSpace(Integer br){
        System.out.printf("%d ",br);
    }
}
