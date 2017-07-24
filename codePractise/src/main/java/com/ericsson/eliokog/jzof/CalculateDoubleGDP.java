package com.ericsson.eliokog.jzof;

/**
 * Created by eliokog on 2017/4/11.
 */
public class CalculateDoubleGDP {


    public static double calculateGDP(double a){

        double cal = a;
        int i=0;
        while(cal<2*a){
            cal= 1.09*cal;
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(calculateGDP(100.09));
    }

}
