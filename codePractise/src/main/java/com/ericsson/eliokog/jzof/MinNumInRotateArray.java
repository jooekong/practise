package com.ericsson.eliokog.jzof;

/**
 * Created by eliokog on 2017/4/7.
 */
public class MinNumInRotateArray {

    /**
     * rotated array like {3,4,5,1,2} find the minimal value
     * @param array
     * @return
     */
    public static int minNum(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("invalid array");
        }
        for (int i = 1; i < array.length - 1; i++) {
            if (array[i] < array[i - 1]) {
                return array[i];
            }
        }
        return array[0];
    }

    public static void main(String[] args) {

        int[] array = {4, 5, 6, 7, 1, 2, 3};
        System.out.println(minNum(array));
    }

}
