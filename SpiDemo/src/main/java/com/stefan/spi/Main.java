package com.stefan.spi;

import java.util.ServiceLoader;

/**
 * @author stefanyang
 */
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Search> searches = ServiceLoader.load(Search.class);
        for (Search search : searches) {
            search.searchDoc("keyword");
        }
    }
}