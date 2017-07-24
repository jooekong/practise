package com.ericsson.eliokog.concurrent.GuardedSuspension;


import java.util.concurrent.Callable;

import static javafx.scene.input.KeyCode.V;

/**
 * Created by eliokog on 2016/11/17.
 */
public interface Blocker {

     <V>V callWithGuard(GuardedAction<V> guardedAction );

     void signalAfter();

     void signal(Callable<Boolean> call);

     void broadcastAfter();

     void broadcast();
}
