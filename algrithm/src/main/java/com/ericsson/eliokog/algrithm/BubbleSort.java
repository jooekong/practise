package com.ericsson.eliokog.algrithm;

import java.util.Arrays;

/**
 * Created by eliokog on 2017/6/26.
 */
public class BubbleSort {
    private static int[] bubbleSort(int[] array){
        for(int i =0; i< array.length; i++){
            for(int j=0; j<i; j++){
                if(array[i]> array[j]){
                    int temp =array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {3,2,1,4,5,7,6};
        System.out.println(Arrays.toString(bubbleSort(array)));
    }
}
