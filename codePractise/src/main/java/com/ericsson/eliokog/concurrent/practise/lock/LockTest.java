package com.ericsson.eliokog.concurrent.practise.lock;

import org.apache.zookeeper.txn.TxnHeader;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eliokog on 2017/7/13.
 */
public class LockTest {
    private static volatile boolean isRunning = false;
    private static volatile Lock lock = new ReentrantLock();
    private static volatile Condition c1 = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new LockTask(), "lc-1").start();
        new Thread(new LockTask(), "lc-2").start();
        Thread.sleep(2000);
        for (int i=0; i<2; i++){
        lock.lockInterruptibly();
        try{
            c1.signalAll();

        }finally {
            lock.unlock();
            Thread.sleep(3000);
        }}
        for (int i=0; i<3; i++){
            lock.lockInterruptibly();
            try{
                isRunning = true;
                c1.signalAll();
            }finally {
                lock.unlock();
                Thread.sleep(3000);
            }}
    }

    static class LockTask implements Runnable {

        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                while (!isRunning) {
                    System.out.println("isrunning status is : " + isRunning + " Thread is " + Thread.currentThread().getName());
                    try {
                        c1.await();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("executing with thread: " + Thread.currentThread().getName());

            } finally {
                lock.unlock();
            }

        }
    }
}
