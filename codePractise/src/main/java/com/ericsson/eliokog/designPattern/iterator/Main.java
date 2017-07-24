package com.ericsson.eliokog.designPattern.iterator;

import java.util.Iterator;

/**
 * Created by eliokog on 2017/7/11.
 */
public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(2);
        bookShelf.add(new Book("sanguo", "luoguanzhong"));
        bookShelf.add(new Book("sanguo1", "luoguanzhong1"));
        bookShelf.add(new Book("sanguo2", "luoguanzhong2"));
        Iterator<Book> itr = bookShelf.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
    }
}
