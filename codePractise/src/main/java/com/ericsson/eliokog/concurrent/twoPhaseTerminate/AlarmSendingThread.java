package com.ericsson.eliokog.concurrent.twoPhaseTerminate;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eliokog on 2017/7/20.
 *
 *
 */
public class AlarmSendingThread extends AbstractTerminatableThread {

    private final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue(1000);

    private final ConcurrentHashMap<String, AtomicInteger> alarmRegistry = new ConcurrentHashMap<>();

    public boolean sendAlarm(String alarm) {
        if (!terminateToken.isShutdown()) {
            System.out.println("alarm thread was shutdown, reject alarm: " + alarm);
            return false;
        }
        if (alarmRegistry.putIfAbsent(alarm, new AtomicInteger(1)) == null) {
            terminateToken.counts.getAndIncrement();
            queue.add(alarm);
        }
        return true;
    }

    @Override
    protected void doRun(){
        try {
            String alarm = queue.take();
            terminateToken.counts.getAndDecrement();
            alarmRegistry.remove(alarm);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
