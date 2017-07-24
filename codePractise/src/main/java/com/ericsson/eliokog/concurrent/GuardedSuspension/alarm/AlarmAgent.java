package com.ericsson.eliokog.concurrent.GuardedSuspension.alarm;

import com.ericsson.eliokog.concurrent.GuardedSuspension.GuardedAction;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by eliokog on 2017/7/13.
 */
public class AlarmAgent {

    private volatile boolean isConnected = false;

    private final Blocker blocker = new BlockerImpl();

//    private final GuardedAction guardedAction = new GuardedObjImpl( ()->isConnected);

//    public AlarmAgent(GuardedAction guardedAction) {
//        this.guardedAction = guardedAction;
//    }

    public void init() {
        Thread connectThread = new Thread(new ConnectTask(), "conenctThread");
        connectThread.start();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleWithFixedDelay(() -> {
            System.out.println("checking heartbeat");
            if(!checkConnection()){
                onDisconnect();
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }


    private boolean checkConnection(){
        return true;
    }

    public void disconnect() {

    }

    public void sendAlarm(String alarm) {
        GuardedAction guardedAction = new GuardedAction(() -> isConnected) {
            @Override
            public Object call() throws Exception {
                System.out.println("sending alarm... " + alarm);
                return null;
            }
        };

        try {
            Thread.sleep(1000);
            blocker.callWithGuard(guardedAction);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onConnect() throws Exception {
        blocker.signalAfter(()->{
            isConnected = true;
            return true;
        });
    }

    private void onDisconnect() {
        isConnected = false;
    }

    class ConnectTask implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                onConnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
