package com.ericsson.eliokog.jzof;

/**
 * Created by eliokog on 2017/4/6.
 */
public class Singleton {

    /**
     * load on startup
     */
    private static final Singleton singleton = new Singleton();


    private Singleton(){}

    public static Singleton getInstance(){
        return singleton;
    }

    /**
     * lazy load
     */

    private static volatile Singleton instance = null;

    public static Singleton getSingleton() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    //here is not thread safe as some one could get a non-fully initialized instance
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
