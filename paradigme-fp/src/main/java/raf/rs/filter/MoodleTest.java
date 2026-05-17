package raf.rs.filter;

import java.util.List;

public class MoodleTest {

    public static void main(String[] args) {
        List<Integer> brojevi = List.of(2,40,3,10,9,1,4,20);
        Integer rez = brojevi.stream().filter(x->x%10==0).reduce(100,(x,y)->x/10 + y/10);
        System.out.println(rez);
    }
}
