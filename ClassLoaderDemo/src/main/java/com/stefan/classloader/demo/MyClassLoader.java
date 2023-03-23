package com.stefan.classloader.demo;

import java.io.*;

/**
 * @description:
 * @author: stefanyang
 * @date: 2023/3/23 20:03
 * @version: 1.0
 */
public class MyClassLoader extends ClassLoader {

    private String root;

    public MyClassLoader(String root) {
        this.root = root;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] classData = loadClassData(name);
            if (classData == null) {
                throw new ClassNotFoundException();
            } else {
                return defineClass(name, classData, 0, classData.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private byte[] loadClassData(String name) throws FileNotFoundException {
        String path = root + File.separator + name.replace(".", File.separator) + ".class";
        try (InputStream is = new FileInputStream(path);
                ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = is.read(bytes)) != -1) {
                baos.write(bytes, 0, length);
            }
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader("/Users/stefanyang/Desktop/");
        Class<?> loadClass = myClassLoader.loadClass("com.stefan.classloader.demo.Test2");
        Object o = loadClass.getDeclaredConstructor().newInstance();
        System.out.println(o.getClass());
        System.out.println(o.getClass().getClassLoader());
    }
}
