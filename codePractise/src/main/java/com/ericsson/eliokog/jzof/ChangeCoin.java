package com.ericsson.eliokog.jzof;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eliokog on 2017/4/11.
 */
public class ChangeCoin {

    /**
     * 100cents calculate in 1,2,5 cents, each of them should be no less than 8. how many ways
     * @return
     */
    public static List<String> changeCoin(){
        List<String> list = new ArrayList<>();
        for(int x=0; x<8; x++){
            for(int y=0; y<19; y++){
                int z;
                if (( z =36-(5*x+ 2*y))>-1) {
                    list.add("5cents: "+(x+8)+" 2 cents: "+ (y+8)+" 1cent: "+ (z+8));
                }
                }
            }
            return list;
        }

    public static void main(String[] args) {
        changeCoin().forEach(System.out::println);
    }
    }
