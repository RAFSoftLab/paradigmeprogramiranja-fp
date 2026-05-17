package raf.rs.customstructures;

import raf.rs.customstructures.funcstructures.Foldable;
import raf.rs.customstructures.funcstructures.Functor;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class RekList <T> implements Functor<T>, Foldable<T>, Iterable<T>{

    final public boolean empty;

    final private T head;
    final private RekList<T> tail;

    private RekList(T head, RekList<T> tail){
        this.head = head;
        this.tail = tail;
        this.empty = false;
    }

    private RekList(){
        this.head = null;
        this.tail = null;
        this.empty = true;
    }

    public static <T> RekList<T> cons(T head, RekList<T> tail){
        return new RekList(head,tail);
    }

    public static <T> RekList<T> createEmptyList(){
        return new RekList<T>();
    }


    @Override
    public <R> RekList<R> map(Function<T, R> function) {
        if(this.isEmpty()) return RekList.createEmptyList();
        return new RekList(function.apply(head), tail.map(function));
    }




    @Override
    public String toString() {
        if(this.isEmpty()) return "";
        return "[" + this.createString() + "]";
    }

    private String createString(){
        //if(this.isEmpty()) return "";
        return head.toString() + (tail.isEmpty() ? "" : ","+tail.createString());
    }

    public static <T> RekList<T> createFromList(List<T> in){
        if(in==null) return createEmptyList();
        if(in.size()==0) return createEmptyList();
        return new RekList<T>(in.get(0), createFromList(in.subList(1,in.size())));

    }


    @Override
    public <U> U foldL(U acc, BiFunction<U, T, U> function) {
        if(this.isEmpty()) return acc;
        return tail.foldL(function.apply(acc,head),function);
    }

    @Override
    public <U> U foldR(U acc, BiFunction<T, U, U> function) {
        if(this.isEmpty()) return acc;
        return function.apply(head, tail.foldR(acc,function));
    }


    public RekList<T> filter(Predicate<T> predicate){
        if(this.isEmpty()) return RekList.createEmptyList();
        if(predicate.test(head))
            return RekList.cons(head,tail.filter(predicate));
        else
            return tail.filter(predicate);
    }

    public RekList<T> filterFold(Predicate<T> predicate){
        return this.foldR(RekList.createEmptyList(),(x,acc)->predicate.test(x) ? RekList.cons(x,acc) : acc);
    }

    public boolean isEmpty(){
        return empty;
    }

    @Override
    public boolean equals(Object obj) {
        if(!obj.getClass().equals(this.getClass())) return false;
        RekList<T> other = (RekList<T>) obj;
        if(this.isEmpty()) return other.isEmpty();
        return this.head.equals(other.getHead()) && this.tail.equals(other.getTail());
    }

    // concat l2 to this
    public RekList<T> concat(RekList<T> l2){
        if(this.isEmpty())
            return l2;
        else
            return RekList.cons(head, tail.concat(l2));
    }

    public T getHead() {
        return head;
    }

    public RekList<T> getTail() {
        return tail;
    }

    public static <T> RekList<T> flatten(RekList<RekList<T>> rekList){
        return rekList.foldR(RekList.createEmptyList(),(l1,acc)->l1.concat(acc));
    }

    public static <T> RekList<T> of(T... elems){
        return createFromList(Arrays.asList(elems));
    }


    public <U> RekList<U> flatMap(Function<T, RekList<U>> function) {
        return flatten(this.map(function));
    }

    // pocinje od 0
    public Optional<T> ntiElem(int n){
        if(this.isEmpty())
            return Optional.empty();
        else if(n==0)
            return Optional.of(this.head);
        else
            return tail.ntiElem(n-1);
    }

    /*
    zadatak pretvaranje u listu
     */

    public List<T> toList(){
        List<T> retVal = this.foldL(new ArrayList<>(),(acc,el)->{acc.add(el); return acc;});
        return retVal;

    }

    @Override
    public Iterator<T> iterator() {
        return new RekListIterator();
    }

    private class RekListIterator implements Iterator<T>{

        private RekList<T> current = RekList.this; // instanca spoljasnje klase

        @Override
        public boolean hasNext() {
            return !current.isEmpty();
        }

        @Override
        public T next() {
            if (current.isEmpty()) {
                throw new NoSuchElementException();
            }
            T value = current.head;
            current = current.tail;
            return value;
        }
    }

    public RekList<T> addAtTheEnd(T elem){
        if(this.isEmpty())
            return RekList.cons(elem,RekList.createEmptyList());
        else
            return RekList.cons(this.head, this.tail.addAtTheEnd(elem));
    }
}
