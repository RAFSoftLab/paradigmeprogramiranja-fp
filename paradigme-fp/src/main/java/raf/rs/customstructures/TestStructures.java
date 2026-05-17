package raf.rs.customstructures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.prefs.PreferenceChangeListener;
import java.util.stream.Stream;

public class TestStructures {

    public static void main(String[] args) {

        /*

        List<Integer> brojevi = List.of(10,34,15,18,24,13,10,5);
        RekList<Integer> rlBrojeva = RekList.createFromList(brojevi);
        RekList<Boolean> rez = rlBrojeva.map(x->x*x).map(x->x/10).map(x->x%2==0);
        System.out.println(rez);



        int zbir = rlBrojeva.foldL(0,(x,y)->x+y);
        int brojParnih = rlBrojeva.foldL(0,(x,y)->y%2==0 ? x+1 : x);
        System.out.println(brojParnih);

        boolean imaVecih = rlBrojeva.foldL(false,(x,y)->x || y>50);
        System.out.println(imaVecih);

        RekList<Integer> parni = rlBrojeva.foldL(RekList.createEmptyList(), (acc, y)-> y%2==0 ? RekList.cons(y,acc) : acc);
        System.out.println(parni);


        RekList<Integer> parniR = rlBrojeva.foldR(RekList.createEmptyList(), (x, acc)-> x%2==0 ? RekList.cons(x,acc) : acc);
        System.out.println(parniR);

        List<Integer> brojevi1 = List.of(2,3,4);
        RekList<Integer> rlBrojeva1 = RekList.createFromList(brojevi1);
        System.out.println(rlBrojeva1.foldL(0,(x,y)->x-y));
        System.out.println(rlBrojeva1.foldR(0,(x,y)->x-y));

        RekList<Integer> neparni = rlBrojeva.filter(x->x%2==1);
        System.out.println(neparni);
        RekList<Integer> neparni1 = rlBrojeva.filterFold(x->x%2==1);
        System.out.println(neparni1);
*/
       // testConcatFlatMap();
        // testToList();
        //testIterator();

        testPerfomance();
    }

    private static void testZakonitosti(){

        List<Integer> brojevi = List.of(10,34,15,18,24,13,10,5);

        RekList<Integer> rlBrojeva = RekList.createFromList(brojevi);

        Function<Integer, Integer> multiply = (value) -> value * 2;
        Function<Integer, Integer> add      = (value) -> value + 3;

        Function<Integer, Integer> multiplyThenAdd = multiply.andThen(add); // prvo množenje pa sabiranje
        Function<Integer, Integer> addComposeMultiply = multiply.compose(add);  // prvo sabiranje pa množenje



        boolean prvaZakonitost = rlBrojeva.map(Function.identity()).equals(Function.identity().apply(rlBrojeva));
        boolean drugaZakonitost = rlBrojeva.map(multiplyThenAdd).equals(rlBrojeva.map(multiply).map (add));

        System.out.println(prvaZakonitost && drugaZakonitost);
    }

    private static void testConcatFlatMap(){

        RekList<Integer> l1 = RekList.of(10,34,15,18,24,13,10,5);
        RekList<Integer> l2 = RekList.of(0,6,7,10,9,1);
        System.out.println(l1.concat(l2));
        RekList<RekList<Integer>> lista = RekList.of(RekList.of(3,4,5), RekList.of(1,8,10), RekList.of(5,2));
        System.out.println(lista);
        System.out.println(RekList.flatten(lista));

        System.out.println(l1.flatMap(x->RekList.of(x+1,x+2)));
        System.out.println(l1.ntiElem(20));
    }

    private static void testToList(){
        RekList<Integer> l1 = RekList.of(10,34,15,18,24,13,10,5);
        System.out.println(l1.toList());
    }

   private static void testIterator(){
        RekList<Integer> l1 = RekList.of(10,34,15,18,24,13,10,5);
        Iterator<Integer> it= l1.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        for(Integer i:l1){
            System.out.println("foreach "+i);
        }
    }

    private static void testPerfomance(){
        List<Integer> testList = new ArrayList<>();
        int maks = 1000;
        for(int i=0;i<maks;i++){
            testList.add(i);
        }

        RekList<Integer> testRekList = RekList.createEmptyList();
        for(int i=0;i<maks;i++){
            testRekList = testRekList.addAtTheEnd(i);
        }

        Stream<Integer> s = testList.stream();

        long start = System.currentTimeMillis();
        s.flatMap(x-> Stream.of(x+100,x+200,x+300,x+400,x+500));
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Stream time: "+timeElapsed);

        start = System.currentTimeMillis();
        testRekList.flatMap(x-> RekList.of(x+100,x+200,x+300,x+400,x+500));
        finish = System.currentTimeMillis();
        timeElapsed = finish - start;
        System.out.println("RekList time: "+timeElapsed);
    }
}
