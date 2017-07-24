package com.ericsson.eliokog.concurrent.twoPhaseTerminate;

import java.lang.ref.WeakReference;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eliokog on 2017/7/19.
 *
 *
 */
public class TerminateToken {

    private volatile AtomicBoolean isShutdown = new AtomicBoolean(false);

    public volatile AtomicInteger counts = new AtomicInteger(0);

    private final Queue<WeakReference<Terminatable>> coordinateThreads;

    public TerminateToken(){
        this.coordinateThreads = new ConcurrentLinkedQueue<>();
    }
    public TerminateToken(Queue<WeakReference<Terminatable>> coordinateThreads) {
        this.coordinateThreads = coordinateThreads;
    }

    public boolean isShutdown(){
        return isShutdown.get();
    }

    public void setToShutdown(){
        isShutdown.getAndSet(true);
    }

    protected void register(Terminatable thread){
        coordinateThreads.add(new WeakReference<Terminatable>(thread));
    }

    protected void notifyThreadTermination(Terminatable thread){
        WeakReference<Terminatable> wrThread;
        Terminatable otherThread;
        while((wrThread = coordinateThreads.poll())!=null){
            otherThread = wrThread.get();
            if(otherThread!=null&& otherThread!=thread){
                otherThread.terminate();
            }
        }
    }

}
