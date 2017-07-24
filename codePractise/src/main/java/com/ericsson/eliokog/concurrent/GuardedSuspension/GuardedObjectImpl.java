package com.ericsson.eliokog.concurrent.GuardedSuspension;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by eliokog on 2016/11/17.
 */
public class GuardedObjectImpl implements GuardedObject {
    private final boolean state = false;

    @Override
    public void guardedMethod() {

    }

    @Override
    public void stateChange() {
    }
}
