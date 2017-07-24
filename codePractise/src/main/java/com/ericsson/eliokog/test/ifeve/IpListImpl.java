package com.ericsson.eliokog.test.ifeve;

import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by eliokog on 2017/4/19.
 */
public class IpListImpl implements IpList {
    private final static ConcurrentSkipListSet<String> set = new ConcurrentSkipListSet<>();

    @Override
    public boolean isInList(String ip) {
       return  set.add(ip);
    }
}
