package com.ericsson.eliokog.concurrent.GuardedSuspension;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by eliokog on 2016/11/17.21
 */
public class GSPractis {
    private static boolean stat = false;
    private static final Blocker blocker;

    static {
        blocker = new BlockerImpl();
    }

    public static void main(String[] args) throws InterruptedException {

        Predicate predicate = () -> stat;
//        GuardedAction<String> ga = new GuardedAction<String>(predicate) {
//            @Override
//            public void run() {
//                System.out.println("executing");
//            }
//        };
//        Thread t = new Thread(() -> {
//            blocker.callWithGuard(ga);
//        }
//        );
//        t.start();
//        Thread.sleep(1000);
//        Timer timer = new Timer();
//        timer.schedule(new StatTask(), 3000 );


    }

    private static class StatTask extends TimerTask {

        @Override
        public void run() {
            System.out.println("signaling");
            stat = true;
            blocker.signal(() -> true);
        }
    }

}
