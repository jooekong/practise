package com.ericsson.eliokog.problems.arithmetic;

import java.util.Arrays;

/**
 * Created by eliokog on 2017/7/12.
 */
public class ArrayReverseRecursive {

    private static int[] reverseArray(int[] a, int index) {
        if (a.length - index - 1 <= index) {
            return a;
        }
        int temp = a[index];
        a[index] = a[a.length - index - 1];
        a[a.length - index - 1] = temp;
        return reverseArray(a, ++index);
    }

    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,7,8, 9};
        System.out.println(Arrays.toString(reverseArray(a, 0)));
    }
}
