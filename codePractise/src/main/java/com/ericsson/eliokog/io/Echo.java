package com.ericsson.eliokog.io;

import java.io.Serializable;

/**
 * Created by eliokog on 2016/10/9.
 */
public class Echo implements Serializable{
    private String msg;

    public Echo(String msg){
        this.msg=msg;
    }
    public void echo(){
        System.out.println("hi there! " + msg);
    }

    public void setMsg(String msg){
        this.msg=msg;
    }
}
