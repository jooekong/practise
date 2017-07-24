package com.ericsson.eliokog.jzof;

import java.util.Arrays;

/**
 * Created by eliokog on 2017/4/10.
 */
public class BubbleSort {


    public static  int[] bubbleSort(int[] array){

        for(int i=0; i<array.length; i++){
            for(int j=0; j<i; j++){
                if(array[j]>array[i]){
                    swap(array, j, i);
                }
            }
        }
        return array;
    }
    private static void swap(int[] array, int i, int j){
        int temp =array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {100,40, 60, 87, 34, 11, 56, 0};
        bubbleSort(a);
        System.out.println(Arrays.toString(a));//arrays.toString to printout the array
    }
}
