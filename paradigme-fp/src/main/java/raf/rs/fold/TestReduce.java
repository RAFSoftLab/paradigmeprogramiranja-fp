package raf.rs.fold;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestReduce {


    public static void main(String[] args) {
        List<String> imena = Arrays.asList("Marija", "Milica", "Marko", "Anamarija", "Sava");
        Optional<String> najduzeIme = imena.stream().reduce((ime1,ime2)->ime1.length()>ime2.length() ? ime1 : ime2);
        najduzeIme.ifPresent(System.out::println);

        imena.stream().max(java.util.Comparator.comparing(String::length)).ifPresent(System.out::println);

        Optional<String> spojSve = imena.stream().reduce((ime1,ime2)->ime1+ime2);
        spojSve.ifPresent(System.out::println);

        String spojSveSaPocetkom = imena.stream().reduce("Spojena imena: ",(ime1,ime2)->ime1+ime2);
        System.out.println(spojSveSaPocetkom);

        String spojSveNaMSaPocetkom = imena.stream().filter(str->str.startsWith("M")).reduce("Spojena imena: ",(ime1,ime2)->ime1+ime2);
        System.out.println(spojSveNaMSaPocetkom);





    }

}
