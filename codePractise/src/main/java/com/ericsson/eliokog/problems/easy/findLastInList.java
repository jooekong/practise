package com.ericsson.eliokog.problems.easy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by eliokog on 2016/11/28.
 */
public class findLastInList {


    public static <T> T findLast(Collection<T> list) {
//        Iterator<T> ite = list.listIterator();
        Iterator<T> ite1 = list.iterator();
        T t =null ;

        while (ite1.hasNext()) {
            t = ite1.next();
        }
        return t;
    }

    public static <T> T findLastList(List<T> list){
        return list.get(list.size()-1);
    }

    public static <T> T findLastJ8Recursive(List<T> list){
        if(list == null ||list.size()==0){
            throw new NoSuchElementException();
        }
        if (list.size() == 1) {
            return list.get(0);
        }
        return findLastJ8Recursive(list.subList(1, list.size()));
    }


    public static void main(String[] args) {
        List<String> list = Stream.of("a","b", "c").collect(Collectors.toList());
        LinkedList<String> ll = new LinkedList<>(list);

        String s = findLastJ8Recursive(ll);
        System.out.println(s);
    }
}
