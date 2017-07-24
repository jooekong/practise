package com.ericsson.eliokog.jzof;

/**
 * Created by eliokog on 2017/4/7.
 */
public class JumpStair {

    /**
     * someone can jump 1 or 2 or 3 stairs for one time, how many ways he can jump for n staires
     * @return
     */
    public static int jump(int n){
        if(n<1){
            throw new IllegalArgumentException("stairs number must great than 0");
        }
        if(n==1)return 1;
        if(n==2) return 2;
        if(n==3) return 4;
        return jump(n-1)+jump(n-2)+jump(n-3);

    }

    public static void main(String[] args) {
        System.out.println(jump(10));
    }

/*    public static int jumpN(int n){


    }*/
}
