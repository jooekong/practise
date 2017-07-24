package com.ericsson.eliokog.concurrent.GuardedSuspension;

import java.util.concurrent.Callable;

/**
 * Created by eliokog on 2016/11/17.
 */
public abstract class GuardedAction<T> implements Callable<T> {
    public final Predicate guard;

    public GuardedAction(Predicate guard){
        this.guard = guard;
    }

}
