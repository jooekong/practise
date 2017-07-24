package com.ericsson.eliokog.concurrent.practise.connectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by eliokog on 2017/7/12.
 */
public class Main {

    private static final ConnectionPool pool = new ConnectionPool(8);

    private static final CountDownLatch start = new CountDownLatch(1);
    private static final CountDownLatch end = new CountDownLatch(10);
    private static final AtomicInteger got = new AtomicInteger(0),  notGot = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        for(int i=0; i<10; i++){
            new Thread(new ConnectionTaker(20, got, notGot)).start();
        }
        start.countDown();
        end.await();
        System.out.println("total execution is :" + 20*10);
        System.out.println("Got connection count:"+ got.get() );
        System.out.println("not Got connection count:"+ notGot.get() );

    }

    private static class ConnectionTaker implements Runnable {

        Optional<Connection> connection;
        int count;
        AtomicInteger got, notGot;

        private ConnectionTaker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count > 0) {
                try {
                    start.await();
                    connection = pool.getConnection(100);

                    connection.ifPresent(conn -> {
                        try {
                            conn.createStatement();
                            conn.commit();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            pool.releaseConnection(conn);
                            got.getAndIncrement();
                        }
                    });

                    if (!connection.isPresent())
                        notGot.getAndIncrement();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    count--;
                }
            }
            end.countDown();
        }

    }

}
