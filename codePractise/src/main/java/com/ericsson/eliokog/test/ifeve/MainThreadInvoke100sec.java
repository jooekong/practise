package com.ericsson.eliokog.test.ifeve;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by eliokog on 2017/4/19.
 * <p>
 * 一个主线程下有多个子线程任务，主线程必须在100秒内将子线程执行的集合结果进行处理返回，子线程如果在100秒内没有执行完停止执行
 */
public class MainThreadInvoke100sec {
    private static CountDownLatch latch = new CountDownLatch(20);

    public static void main(String[] args) {
//        CountDownLatch latch = new CountDownLatch(20);
//
//        for(int i=0; i<20; i++){
//            new Thread(()->{
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                latch.countDown();
//                System.out.println("finished in Thread:"+ Thread.currentThread().getName());
//
//            }, "Thread-"+i).start();
//        }
//        try {
//            latch.await(1000, TimeUnit.MILLISECONDS);
//            System.out.println("finished in main");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        List<Future<Integer>> futureList = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 20; i++) {
            Future<Integer> f = executor.submit(() -> {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100,1000));
                latch.countDown();
                return ThreadLocalRandom.current().nextInt(0, 10 + 1);
            });
            futureList.add(f);
        }

        try {
            latch.await(1100, TimeUnit.MILLISECONDS);
            System.out.println("Timeout awaiting...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


       int sum = futureList.stream().filter(f-> {
            if(!f.isDone())
                f.cancel(true);
            return !f.isCancelled();
        }).map(f -> {
            try {
                return f.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return 0;
        }).reduce(0, (i, j) -> i + j);

        System.out.println("compute result is :" + sum);
        executor.shutdown();
    }

}
