package com.ericsson.eliokog.problems.easy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by eliokog on 2017/1/4.111
 */
public class FindPalindrome {


    private static <E> boolean isPalindrome(List<E> list) {
        if (list == null) {
            throw new IllegalArgumentException("List can't be null");
        }
        if (list instanceof RandomAccess) {
            for(int i=0, j=size()-1; i< size()>>2; i++, j--){
                if(list.get(i)!=list.get(j)){
                    return false;
                }
            }
        } else {
            ListIterator fwd = list.listIterator();
            ListIterator bwd = list.listIterator(size());
            for(int i=0; i<size()>>1; i++){
                E a = (E) fwd.next();
                E b = (E) bwd.previous();
//                System.out.println("a: "+ a + "b: "+b);
                if(a!=b){
                    return false;
                }
            }
        }
        return true;
    }

    //List is null in ReverseList, just to put method here to demonstrate this appraoch also works
    private static <E> boolean isPalindJ8(List<E> list){
        return Objects.equals(list, ReverseList.reverseIntStream(list));
    }

    private static List<String> list;

    private static int size() {
        return list.size();
    }

    public static void main(String[] args) {
        list = Stream.of("a","b", "c", "c","b","a").collect(Collectors.toList());
//        LinkedList<String> ll = new LinkedList<>(list);
        ArrayList<String> ll = new ArrayList<>(list);
        System.out.println(isPalindrome(ll));
        System.out.println(isPalindJ8(ll));


    }
}
