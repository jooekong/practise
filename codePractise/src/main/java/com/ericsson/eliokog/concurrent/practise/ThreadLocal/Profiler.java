package com.ericsson.eliokog.concurrent.practise.ThreadLocal;

import java.util.concurrent.TimeUnit;

/**
 *
 * Created by eliokog on 2017/7/12.
 */
public class Profiler {

    private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal(){
        @Override
        protected Long initialValue() {
            return System.nanoTime();
        }
    };

    private static void begin(){
        TIME_THREADLOCAL.set(System.currentTimeMillis());
    }

    private static long end(){
        return (System.nanoTime()-TIME_THREADLOCAL.get());
    }

    private static long currentVal(){
        return TIME_THREADLOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Take time:" + end());

        new Thread(()->{
            try {
                System.out.println("Thread1 current value is:" + currentVal());
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread1 Take time:" + end());

        }).start();
    }
}
