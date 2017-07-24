package com.ericsson.eliokog.collection;

import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

import static javafx.scene.input.KeyCode.K;
import static javafx.scene.input.KeyCode.V;

/**
 * Created by eliokog on 2016/11/16.
 *
 */
public class ConcurrentLinkedHashMap<K, V> {

    private volatile LinkedHashMap<K, V> map;


    public ConcurrentLinkedHashMap(){
        map = new LinkedHashMap<K, V>();
    }

    public ConcurrentLinkedHashMap (int capacity){
        map = new LinkedHashMap<K, V>(capacity);
    }

    public synchronized void put(K key, V value){
        map.put(key, value);
    }

    public synchronized void get(K key, V value){
        map.put(key, value);
    }

}
