package com.ericsson.eliokog.concurrent.GuardedSuspension.alarm;

import com.ericsson.eliokog.concurrent.GuardedSuspension.GuardedAction;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eliokog on 2017/7/13.
 */
public class BlockerImpl implements Blocker {

    private final Lock lock;

    private final Condition condition;

    public BlockerImpl() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public BlockerImpl(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public <V> V callWithGuard(GuardedAction<V> guardedObj) throws Exception {
        lock.lockInterruptibly();
        try {
            while (!guardedObj.guard.evaluate()) {
                condition.await();
            }
            return guardedObj.call();
        } finally {
            lock.unlock();
        }

    }

    @Override
    public void signalAfter(Callable<Boolean> statChange) throws Exception {
        lock.lockInterruptibly();
        try{
            if (statChange.call()){
                condition.signal();
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void broadcastAfter(Callable<Boolean> statChange) throws Exception{
        lock.lockInterruptibly();
        try{
            if (statChange.call()){
                condition.signalAll();
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void signal() throws InterruptedException{
        lock.lockInterruptibly();
        try {
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void broadcast() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            condition.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
