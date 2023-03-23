package com.stefan.classloader.demo;

/**
 * @description:
 * @author: stefanyang
 * @date: 2023/3/23 19:52
 * @version: 1.0
 */
public class LoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = LoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        Class<?> test2 = classLoader.loadClass("com.stefan.classloader.demo.Test2");
//        Class<?> aClass = Class.forName("com.stefan.classloader.demo.Test2");
//        Class.forName("com.stefan.classloader.demo.Test2", false, classLoader);
//        Class.forName("com.stefan.classloader.demo.Test2", true, classLoader);
    }
}
