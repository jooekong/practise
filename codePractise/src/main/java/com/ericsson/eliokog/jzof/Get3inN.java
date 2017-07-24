package com.ericsson.eliokog.jzof;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by eliokog on 2017/4/11.
 */
public class Get3inN {

    public static void get3InN(int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < n + 1; i++) {
            list.add(i);
        }
        ListIterator<Integer> iterator = null;
        int k = 1;
        while (list.size() > 1) {
            iterator = list.listIterator();
            while (iterator.hasNext()) {
                int i= iterator.next();
                if (k % 3 == 0) {
                    iterator.remove();
                    System.out.println("Number " + i + " is out!");
                    k =0;
                }
                k++;
            }
        }
        System.out.println("Left number is: "+ list.get(0));
    }

    public static void main(String[] args) {
        get3InN(5);
    }

}
