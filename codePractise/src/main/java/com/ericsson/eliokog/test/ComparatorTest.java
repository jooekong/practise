package com.ericsson.eliokog.test;

import java.util.Collections;
import java.util.Comparator;

/**
 * Created by eliokog on 2017/4/24.
 */
public class ComparatorTest {


    public static void main(String[] args) {

        Comparator<String> comparatorFisrt =  new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        Comparator<String> comparatorSecond=  new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };

        Comparator<String> comparator = comparatorFisrt.thenComparing(comparatorSecond).thenComparing(comparatorSecond);
//        Collections.sort(persons, comparator);


    }
}
