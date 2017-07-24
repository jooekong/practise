package com.ericsson.eliokog.concurrent.GuardedSuspension;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eliokog on 2016/11/17.
 */
public class BlockerImpl implements Blocker{
    private final Lock lock;
    private final Condition condition;
    public BlockerImpl(){
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }
    @Override
    public <V> V callWithGuard(GuardedAction<V> guardedAction) {
        try {
            lock.lockInterruptibly();
            Predicate guard = guardedAction.guard;
            while (!guard.evaluate()) {
                try {
                    condition.await();
                    System.out.println("waiting for signal");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            guardedAction.call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return null;
    }

    @Override
    public void signalAfter() {

    }

    @Override
    public void signal(Callable<Boolean> call) {
        try {
            lock.lockInterruptibly();
            if(call.call()){
                condition.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    @Override
    public void broadcastAfter() {

    }

    @Override
    public void broadcast() {

    }
}
