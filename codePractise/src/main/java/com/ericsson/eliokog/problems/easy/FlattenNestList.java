package com.ericsson.eliokog.problems.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by eliokog on 2017/1/4.
 */
public class FlattenNestList {
    public static void main(String[] args) {
        List list1 = Stream.of("a","b", "c").collect(Collectors.toList());
        List list2 = Stream.of("a1","b1", "c1").collect(Collectors.toList());
        list2.addAll(list1);
        List list = flatNestListJ8(list2);
        list.stream().forEach(System.out::println);
    }

    public static <E> List<E> flatNestList(List<?> list){
        List newList = new ArrayList<E>();
        list.forEach(e->{
            if(e instanceof List){
                newList.addAll(flatNestList((List<?>)e));
            }else{
                newList.add(e);
            }
        });
        return newList;
    }


    public static <E> List<E> flatNestListJ8(List<?> list){
      return list.stream().flatMap(e -> e instanceof List ? flatNestListJ8((List<?>) e).stream(): Stream.of(e)).map(e -> (E) e).collect(Collectors.toList());
    }
}
