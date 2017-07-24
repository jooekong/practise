package com.ericsson.eliokog.jzof;

/**
 * Created by eliokog on 2017/4/7.
 */
public class PrintWithoutLoop {

    public static void printWithoutloop(int n) {
        if(n>=5000){
            return;
        }
        printIncrease(n, n);
    }

    public static void printIncrease(int n, int origin) {

        if (n < 5000) {
            System.out.println(n);
            printIncrease(2 * n, origin);
        } else {
            System.out.println(n);
            printDecrease(n, origin);
        }
    }

    public static void printDecrease(int n, int origin) {
        System.out.println(n);
        if (n == origin) {
            return;
        } else {
            printDecrease(n / 2, origin);
        }
    }

    public static void main(String[] args) {
        printWithoutloop(222);
    }
}
