package com.ericsson.eliokog.jzof;

/**
 * Created by eliokog on 2017/4/10.
 */
public class FastSort {

    public static void quickSort(int[] data, int start, int end) {
        // 设置关键数据key为要排序数组的第一个元素，
        // 即第一趟排序后，key右边的数全部比key大，key左边的数全部比key小
        int key = data[start];
        // 设置数组左边的索引，往右移动比key大的数
        int i = start;
        // 设置数组右边的索引，往左移动比key小的数
        int j = end;
        // 如果左边索引比右边索引小，则还有数据没有排序
        while (i < j) {
            while (data[j] > key && j > i) {
                j--;
            }
            data[i] = data[j];

            while (data[i] < key && i < j) {
                i++;
            }
            data[j] = data[i];
        }

        // 此时 i==j
        data[i] = key;

        // 递归调用
        if (i - 1 > start) {
            // 递归调用，把key前面的完成排序
            quickSort(data, start, i - 1);
        }
        if (i + 1 < end) {
            // 递归调用，把key后面的完成排序
            quickSort(data, i + 1, end);
        }
    }

    public static void quickSort1(int[] a, int start, int end) {
        int key = a[start];
        int i = start, j = end;
        while (j > i) {
            while (i < j && a[j] > a[i]) {
                j--;
            }
            a[i] = a[j];
            while (i < j && a[j] < a[i]) {
                i++;
            }
            a[j] = a[i];
        }

    }
}
