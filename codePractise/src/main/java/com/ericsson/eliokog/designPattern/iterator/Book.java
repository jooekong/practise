package com.ericsson.eliokog.designPattern.iterator;


/**
 * Created by eliokog on 2017/7/11.
 */
public class Book {

    private String name;
    private String author;

    public Book(String name, String author){
        this.name = name;
        this.author = author;
    }

    public String toString(){
        return "book name: "+ name +" Author: "+author;
    }
}
