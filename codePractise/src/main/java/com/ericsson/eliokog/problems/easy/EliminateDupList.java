package com.ericsson.eliokog.problems.easy;

import java.util.List;
import java.util.ListIterator;

/**
 * Created by eliokog on 2017/1/6.
 */
public class EliminateDupList {



    private static <E> List<E> elimimateDup(List<E> list){
        if(list == null){
            throw new IllegalArgumentException("List can't be null");
        }
        E temp = null;
        ListIterator<E> iterator = list.listIterator();
        while(iterator.hasNext()){
            E e = iterator.next();
            if( e == temp){
                iterator.remove();
            }else{
                temp = e;
            }
        }
        return list;
    }


    public static void main(String[] args) {

    }
}
