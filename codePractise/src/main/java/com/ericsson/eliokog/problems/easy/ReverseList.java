package com.ericsson.eliokog.problems.easy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/**
 * Created by eliokog on 2016/12/30.
 */
public class ReverseList {

    public static void reverseList(List<String> list){
        if(list instanceof LinkedList) {
            ListIterator ite = list.listIterator();
            while(ite.hasNext()){
                ite.next();
            }
           while( ite.hasPrevious()){
               System.out.println(ite.previous());
           }
        }else{
            for(int i= list.size(); i>0; i++){
                System.out.println(list.get(i));
            }
        }


    }

    /**
     * similiar to Collections.reverse(list);
     * @param list
     */
    public static <E> List  reverseListAdvanced(List<E> list){

        if(list instanceof RandomAccess){
            for(int i=0,  j= list.size()-1; i< list.size()>>1; i++, j-- ){
                E temp = list.get(j);
                list.set(j,list.get(i));
                list.set(i, temp);
                //advanced approach
                //l.set(i, l.set(j, l.get(i))); --- set() returns the old value
            }
        }else{
            ListIterator forward =list.listIterator();
            ListIterator backward =list.listIterator(list.size());
            for(int i=0, j = list.size()-1; i< list.size()>>1; i++,j--){
                Object tmp = forward.next();
                forward.set(backward.previous());
                backward.set(tmp);

            }

        }

        return list;
    }

    public static <E> List reverseIntStream(List<E> list){
        if(list==null){
            throw new IllegalArgumentException("List can't be null!");
        }
        return IntStream.iterate(size()-1, e -> e-1).limit(size()).mapToObj(list::get).collect(Collectors.toList());
    }

    public static int size(){
        return list.size();
    }

    private static List<String> list;
    public static void main(String[] args) {
        list = Stream.of("a","b", "c").collect(Collectors.toList());
        LinkedList<String> ll = new LinkedList<>(list);
        System.out.println(reverseIntStream(ll));

    }
}
