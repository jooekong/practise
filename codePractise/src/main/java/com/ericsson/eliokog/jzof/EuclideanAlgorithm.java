package com.ericsson.eliokog.jzof;

/**
 * Created by eliokog on 2017/4/11.
 */
public class EuclideanAlgorithm {

    public static int getNumber(int i, int j){
        if(i>j){
            int m;
            if((m=i%j)!=0){
               return getNumber(j,m);
            }else{
                return j;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getNumber(876, 6));
    }
}
