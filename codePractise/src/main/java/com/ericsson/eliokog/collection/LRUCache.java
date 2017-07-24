package com.ericsson.eliokog.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by eliokog on 2016/11/14.
 */
public class LRUCache <K, V> {
    private Entry head;

    private Entry tail;

    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_SIZE = Integer.MAX_VALUE;
    private  int capacity;

    Lock lock = new ReentrantLock();
    private ConcurrentHashMap<K, Entry> map;

    public LRUCache() {
        this(DEFAULT_CAPACITY);
    }

    public LRUCache(int capacity){
        map = new ConcurrentHashMap<K, Entry>(Math.round(capacity*0.75f));
        this.capacity = capacity;
    }

    public void add(K key, V value) {
        try {
            lock.lock();
            Entry e = getEntry(key, value);
            map.put(key, e);
            moveToHead(e);
            if (removeOldest()) {
                tail.before.after = null;
                tail = tail.before;
            }
        } finally {
            lock.unlock();
        }
    }

    public V get(K key) {
        Entry e = map.get(key);
        try {
            lock.lock();
            if (e.before != null) {
                if (e.after != null) {
                    e.before.after = e.after;
                } else {
                    e.before.after = null;
                    tail = e.before;
                }
            }
        } finally {
            lock.unlock();
        }
        return e.getValue();
    }

    private boolean removeOldest(){

        return  size()>capacity;
    }

    private void moveToHead(Entry e){
        head.before = e;
        e.after.before = e.before;
        e.after = head;
        head = e;
    }

    private Entry getEntry(K Key, V value){
        return new Entry(Key, value);
    }

    private int size(){
        return map.size();
    }

    private class Entry implements Map.Entry<K, V>{
        K key; V value;
        Entry before, after;

        private Entry(K k, V v){
            this.key = k;
            this.value = v;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            this.value = value;
            return value;
        }
    }


}
