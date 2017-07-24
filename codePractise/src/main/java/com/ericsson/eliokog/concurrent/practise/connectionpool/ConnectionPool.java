package com.ericsson.eliokog.concurrent.practise.connectionpool;


import java.sql.Connection;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Created by eliokog on 2017/7/12.
 */
public class ConnectionPool {

    private final LinkedList<Connection> pool;

    public ConnectionPool(int poolSize){
        pool = new LinkedList<>();
        for(int i=0; i<poolSize; i++){
            pool.add(ConnectionDriver.createConnection());
        }
    }

    public Optional<Connection> getConnection(long miles) throws InterruptedException {
        synchronized (pool){
            long future = System.currentTimeMillis()+miles;
            long remaining = miles;
            while (pool.isEmpty()&& remaining>0){
                pool.wait(remaining);
                remaining = future - System.currentTimeMillis();
            }
            return !pool.isEmpty()?Optional.of(pool.removeFirst()):Optional.empty();
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }


}
