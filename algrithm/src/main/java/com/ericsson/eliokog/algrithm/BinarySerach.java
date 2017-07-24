package com.ericsson.eliokog.algrithm;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eliokog on 2017/6/26.
 */
public class BinarySerach {

    private static boolean binarySearch(int[] arrays, int value){
        int start =0, end =arrays.length-1, mid =1;
        while(start <= end) {
            if (value > arrays[(mid = (start + end) / 2)]) {
                start = mid + 1;
            } else if (value < arrays[mid]) {
                end = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arrays = {1,2,3,4,5,6, 7};
        System.out.println(binarySearchRecursive(arrays, 7));
    }

    private static int binarySearchRecursive(int[] arrays, int value){
      return  searchRecursive(arrays,value, 0, arrays.length);
    }
    private static int searchRecursive(int[] arrays, int value, int start, int end){
        if(start > end){
            return -1;
        }
        int mid = (start + end)/2;
        if(arrays[mid] < value){
            start = mid+1;
           return searchRecursive(arrays,value, start, end );
        } else if(arrays[mid] > value){
            end = mid-1;
            return  searchRecursive(arrays,value, start, end );
        } else {
            return mid;
        }
    }

}
