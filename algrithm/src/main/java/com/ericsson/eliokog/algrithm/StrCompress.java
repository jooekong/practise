package com.ericsson.eliokog.algrithm;

/**
 * Created by eliokog on 2017/6/26.
 */
public class StrCompress {

    private static String compressStr(String str) {
        StringBuilder sb = new StringBuilder();
        int i, j=1;
        for (i = 0; i < str.length(); ) {
            sb.append(str.charAt(i));
            while(i+j<str.length()&&str.charAt(i)==str.charAt(i+j)){
                j++;
            }
            if(j>1){
                sb.append(j);
            }
            i= i+j;
            j=1;
        }
        return str.length()==sb.toString().length()?str:sb.toString();
    }

    public static void main(String[] args) {
        String str = "aaa33a1234777";
        System.out.println(compressStr(str));
    }

}
