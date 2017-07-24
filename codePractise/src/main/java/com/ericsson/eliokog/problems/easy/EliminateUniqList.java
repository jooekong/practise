package com.ericsson.eliokog.problems.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Created by eliokog on 2017/1/6.
 */
public class EliminateUniqList {

    public static void main(String[] args) {

    }

    private static <E> List<E> eliminateUniq(List<E> list){
        Objects.requireNonNull(list);
        HashSet<E> set = new HashSet<E>(list);
        return new ArrayList<E>(set);
    }
}
