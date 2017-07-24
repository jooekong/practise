package com.ericsson.eliokog.problems.easy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;


/**
 * Created by eliokog on 2016/11/29. l
 */
public class FindLastButOnelist {

    /**
     * for list
     * @param list 1
     * @param <T>1
     * @return1
     */
    public static <T> T findLastButOneList(List<T> list){
        if(list ==null || list.size()<2){
            throw new NoSuchElementException();
        }
        return list.get(list.size()-2);
    }

    public static <T> T findLastButOneCollection(Collection<T> collection){
        if(collection ==null || collection.size()<2){
            throw new NoSuchElementException();
        }
        Iterator<T> ite = collection.iterator();
        T t1 =null;
        int i=0;
        while(ite.hasNext()){
            i++;
            T temp = ite.next();
            if(i==collection.size()-1){
                t1 = temp;
                break;
            }
        }
        return t1;
    }

    public static void main(String[] args) {
        List<String> list = Stream.of("a","b", "c").collect(Collectors.toList());
        LinkedList<String> ll = new LinkedList<>(list);

        String s = findLastButOneCollection(ll);
        System.out.println(s);
    }

}
