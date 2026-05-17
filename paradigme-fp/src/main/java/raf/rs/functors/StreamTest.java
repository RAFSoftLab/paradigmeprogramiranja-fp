package raf.rs.functors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        List<String> listaStringova = new ArrayList<>();
        listaStringova.add("ddd2");
        listaStringova.add("aa a2");
        listaStringova.add("b bb1");
        listaStringova.add("aa a1");
        listaStringova.add("b b b3");
        listaStringova.add("c c c");
        listaStringova.add("b b b2");
        listaStringova.add("d d d1");
        Stream<String> stream = listaStringova.stream();

        stream.map(String::trim).map(str->str.replace("aa","a")).map(String::length).forEach(System.out::println);


        List<Integer> brojevi = List.of(10,34,15,18,24,13,10,5);
        brojevi.stream().map(br->br%2).forEach(System.out::println);
    }
}
