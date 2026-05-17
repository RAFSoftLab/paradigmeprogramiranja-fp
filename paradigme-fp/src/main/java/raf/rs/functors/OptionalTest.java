package raf.rs.functors;

import raf.rs.model.Nastavnik;
import raf.rs.model.Zaposleni;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;

public class OptionalTest {

    public static void main(String[] args) {
        for(int i=0;i<150;i++) {
            String str = vratiStr();
            System.out.println(str);
            Optional<String> rez = Optional.ofNullable(str);
            rez.map(s->s.replaceAll(" ","")).map(String::toUpperCase).map(String::length).ifPresent(System.out::println);

            Optional<Nastavnik> nasOpt = Optional.of(new Nastavnik("Marko","Markovic"));

            nasOpt.map(new Ispis()).ifPresent(System.out::println);

            nasOpt.map(z->z.getIspis());


        }
    }

    public static String vratiStr(){
        Random rand = new Random();
        int a = rand.nextInt();
        if(a%3==0)
            return null;
        else if(a%3==1)
            return "moj prvi string";
        else
            return "aaaaaa";
    }

    static class Ispis implements Function<Zaposleni,String> {

        @Override
        public String apply(Zaposleni zaposleni) {
            return zaposleni.getIme()+" " + zaposleni.getPrezime();
        }
    }


}
