package com.ericsson.eliokog.jzof;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliokog on 2017/4/11.
 */
public class GetNumber {

    /**
     * get number between 10~1000 that divid 7 remainder 5
     *
     * @return
     */
    public static List<Integer> getNumber(){
        List<Integer> list = new ArrayList<>();
        for(int i=10; i<1001; i++){
            if(i%7!=5) {
                continue;
            }
            if(i%5!=3){
                continue;
            }
            if(i%3!=1){
                continue;
            }
            list.add(i);
        }
    return list;
    }

    public static void main(String[] args) {
        System.out.println(getNumber());
    }
}
