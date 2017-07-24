package com.ericsson.eliokog.jzof;

import java.util.Arrays;

/**
 * Created by eliokog on 2017/4/6.
 */
public class ReplaceSpace {

    public static void replaceSpace(char[] chars){

        for(int i=0; i<chars.length; i++){
            if(chars[i]== ' '){
                chars[i]='c';
            }
        }
    }

    public static void main(String[] args) {
        char[] chars = {'a', ' ', ' ','b'};
        replaceSpace(chars);
        System.out.println(chars);
    }
}
