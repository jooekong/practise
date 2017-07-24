package com.ericsson.eliokog.concurrent.GuardedSuspension.alarm;

import com.ericsson.eliokog.concurrent.GuardedSuspension.GuardedAction;

import java.util.concurrent.Callable;

/**
 * Created by eliokog on 2017/7/13.
 */
public interface Blocker {

   <V> V callWithGuard(GuardedAction<V> guardedObj) throws InterruptedException, Exception;

    void signalAfter(Callable<Boolean> statChange) throws Exception;

    void broadcastAfter(Callable<Boolean> statChange) throws Exception;

    void signal() throws InterruptedException;

    void broadcast() throws InterruptedException;
}
