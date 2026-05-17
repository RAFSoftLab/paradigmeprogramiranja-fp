package raf.rs.funcinterfejsi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TestMoodlePrimeri {

    public static Function<String, Function<Integer,String>> obradi(){
        return x->y->x;
    }

    public static void main(String[] args) {
        List<String> lista = new ArrayList<>();
        lista.add("  mama   ");
        lista.add(null);
        lista.add("ana ");
        lista.add(null);
        for(String str:lista){
            Optional<String> pom = Optional.ofNullable(str);
            Optional<Integer> rez = pom.map(s->s.trim()).map(s->s.length());
            rez.ifPresent(System.out::println);
        }


        Function<String,Function<Integer,String>> primer = str->br->str.substring(br);
    }
}
