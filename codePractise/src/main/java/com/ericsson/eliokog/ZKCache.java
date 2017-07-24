package com.ericsson.eliokog;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by eliokog on 2016/9/29.
 * This class can be used to cache all the znodes under a specific path and reflect the node changes in short time.
 */
public class ZKCache<T> {

    private ZooKeeper connection;
    private String path;
    private final ConcurrentHashMap<String, T> cache = new ConcurrentHashMap<>();
    private static final String DELIMETER = "/";

    public ZKCache(ZooKeeper connection, String path) {
        this.connection = connection;
        this.path = path;

    }

    public Map<String, T> cacheAll() {
        try {
            byte[] data = connection.getData(path, new NodeWatcher(), null);
            if (getObject(data) != null)
                cache.putIfAbsent(path, getObject(data));
            cacheChild(path);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        return getCache();
    }

    private void cacheChild(String path) {
        try {
            List<String> childrenList = connection.getChildren(path, null);
            childrenList.stream().forEach(childPath -> {
                String fullPath = path + DELIMETER + childPath;
                byte[] data = new byte[0];
                try {
                    data = connection.getData(fullPath, new NodeWatcher(), null);
                    cache.putIfAbsent(fullPath, getObject(data));
                    cacheChild(fullPath);
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private class NodeWatcher implements Watcher {

        @Override
        public void process(WatchedEvent event) {
            if (event.getType() == Event.EventType.NodeDataChanged) {
                try {
                    byte[] data = connection.getData(event.getPath(), new NodeWatcher(), null);
                    cache.put(event.getPath(), getObject(data));
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (event.getType() == Event.EventType.NodeDeleted) {
                cache.remove(event.getPath());
            }
            //if new child is create, add it to the cache as well
            else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                String parent = event.getPath();
                try {
                    List<String> children = connection.getChildren(parent, null);
                    children.stream().forEach(child -> {
                        String fullPath = parent + DELIMETER + child;
                        try {
                            byte[] data = connection.getData(event.getPath(), new NodeWatcher(), null);
                            cache.putIfAbsent(fullPath, getObject(data));
                        } catch (KeeperException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private T getObject(byte[] bytes) {
        try {
            ByteArrayInputStream bbis = new ByteArrayInputStream(bytes);
            T t = (T) new ObjectInputStream(bbis).readObject();
            return t;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, T> getCache() {
        return Collections.unmodifiableMap(cache);
    }
}
