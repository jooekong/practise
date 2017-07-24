package com.ericsson.eliokog.jzof;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliokog on 2017/4/10.
 */
public class Find7Number {

    public static void main(String[] args) {

        System.out.println(getNum7(200));
    }
    public static List<Integer> getNum7(int a){
        List<Integer> list =  new ArrayList<>();
        for(int i=1; i<a; i++){
            if(i%7 ==0){
                list.add(i*i);
            }
        }
        return list;
    }
}
