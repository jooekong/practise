package com.ericsson.eliokog.jzof;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliokog on 2017/4/10.
 */
public class DaffodilNumber {

    public static List<Integer> getDaffodilNumber() {
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            int hundred = i / 100;
            int ten = (i - hundred * 100) / 10;
            int units = (i - hundred * 100 - ten * 10);
            if (i == (hundred * hundred * hundred + ten * ten * ten + units * units * units)) {
                list.add(i);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println(getDaffodilNumber());
    }
}
