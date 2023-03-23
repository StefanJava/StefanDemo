package com.stefan.classloader.demo;

/**
 * @description:
 * @author: stefanyang
 * @date: 2023/3/23 19:47
 * @version: 1.0
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
}
