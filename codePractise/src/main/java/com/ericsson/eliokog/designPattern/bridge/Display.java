package com.ericsson.eliokog.designPattern.bridge;

/**
 * Created by eliokog on 2017/7/12.
 */
public class Display {
    private DisplayImpl displayImpl;

    public Display(DisplayImpl displayImpl){
        this.displayImpl = displayImpl;
    }

    void open(){
        displayImpl.open();
    }

    void read(){
         displayImpl.read();
    }

    void display(){
        open();
    }
}
