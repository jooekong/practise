package com.ericsson.eliokog.designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by eliokog on 2017/7/11.
 */
public class BookShelf {

    private final ArrayList<Book> list;

    public BookShelf(int size ){
        list = new ArrayList<>(size);
    }

    public Iterator<Book> iterator(){
        return new BookShelfIterator();
    }

    public void add(Book book){
        list.add(book);
    }


    class BookShelfIterator implements Iterator<Book>{

        private  int index;

        public BookShelfIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index<list.size();
        }

        @Override
        public Book next() {
            Book book = list.get(index);
            index++;
            return book;
        }
    }
}
