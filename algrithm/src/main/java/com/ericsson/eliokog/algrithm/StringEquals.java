package com.ericsson.eliokog.algrithm;

/**
 * Created by eliokog on 2017/6/26.
 */
public class StringEquals {

    public static void main(String[] args) {
        String str1 = "abc";
        String str2 = "abc";
        String str3 =  new String("abc");
        String str4 = new String("abc");
        System.out.println(str1==str2);
        System.out.println(str1==str3);
        System.out.println(str1.equals(str3));
        System.out.println(str3 == str4);
        System.out.println(str4.equals(str3));

    }
}
