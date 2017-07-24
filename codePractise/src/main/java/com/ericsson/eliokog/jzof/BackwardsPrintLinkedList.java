package com.ericsson.eliokog.jzof;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by eliokog on 2017/4/6.
 *
 */
public class BackwardsPrintLinkedList {

    public static final <E> void reversePrint(LinkedList<E> list){

        ListIterator<E> iterator = list.listIterator(list.size());
        while (iterator.hasPrevious()){
            System.out.println(iterator.previous());
        }
    }

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("a", "b", "c"));
        reversePrint(list);
    }
}
