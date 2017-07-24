package com.ericsson.eliokog.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by eliokog on 2017/4/1.
 */
public class IntergeBoxingUnboxingTest {


    public static void main(String[] args) {
        int i =12229;
        int m = 12229;
        Map<Integer, String> map = new HashMap<>();
        map.put(i,"21231231");
        Integer a1 = new Integer(12229);
        Integer a2 = new Integer(12229);
        System.out.println(a1==a2);


    }
}
