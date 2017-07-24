package com.ericsson.eliokog.concurrent.GuardedSuspension;

/**
 * Created by eliokog on 2016/11/17.
 */
public interface GuardedObject {

    public void guardedMethod();

    public void stateChange();
}
