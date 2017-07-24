package com.ericsson.eliokog.test;

import javax.swing.plaf.TableHeaderUI;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eliokog on 2017/6/28.
 */
public class ReadWriteLock {

    private Object mutex = new Object();

    private volatile AtomicInteger wLockCount =  new AtomicInteger(0);

    Thread lockedThread = null;

    private volatile AtomicInteger readCount = new AtomicInteger(0);

//    public void readLock() {
//        if (wLockCount.get() >0) {
//            return false;
//        } else {
//            if (!isLocked) {
//                readCount.getAndIncrement();
//                return true;
//            }
//            return false;
//        }
//    }

    public void readUnlock() {
        readCount.getAndDecrement();
    }

    public void writerLock() throws InterruptedException {
        synchronized (mutex) {
            while(wLockCount.intValue()!=0 ||lockedThread!= Thread.currentThread()){
                mutex.wait();
            }
            wLockCount.getAndIncrement();
            lockedThread = Thread.currentThread();
        }
    }

    public void writeUnlock(){
        synchronized (mutex) {
            if(wLockCount.intValue()== 1 &&lockedThread== Thread.currentThread()){
                wLockCount.getAndIncrement();
                lockedThread = null;
            }else if(wLockCount.intValue() >1&&lockedThread== Thread.currentThread()){
                wLockCount.getAndIncrement();
            } else{
                throw new IllegalStateException("wrong lock stat");
            }
            mutex.notify();
        }

    }

}
