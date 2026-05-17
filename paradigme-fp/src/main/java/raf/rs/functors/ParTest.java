package raf.rs.functors;

public class ParTest {

    public static void main(String[] args) {
        Par<Integer> parInt = Par.of(4,5);
        System.out.println(parInt.map(x->x*2).map(x->x+20));


    }
}
